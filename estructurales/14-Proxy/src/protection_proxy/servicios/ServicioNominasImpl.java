package estructurales.proxy.protection_proxy.servicios;

import estructurales.proxy.protection_proxy.dao.EmpleadoDao;
import estructurales.proxy.protection_proxy.dao.NominaDao;
import estructurales.proxy.protection_proxy.modelo.Empleado;
import estructurales.proxy.protection_proxy.modelo.Meses;
import estructurales.proxy.protection_proxy.modelo.Nomina;

/** RealSubject */
class ServicioNominasImpl implements ServicioNominas {
		
	/*
	 * La construcción de este objeto puede ser computacionalmente
	 * costosa. Cuando es así, el hecho de que el proxy sostenga
	 * una referencia hacia este objeto evita este coste.
	 */
    public ServicioNominasImpl() {
        try {            
        	System.out.println("Construyendo un objeto ServicioNominasImpl...");
        	// Para ver un beneficio añadido del patrón proxy podemos
        	// simular que la creación de este objeto es costosa
            //Thread.sleep(5000);
        	Thread.sleep(50);
            System.out.println("...hecho");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Si la nomina ya existe y las horas trabajadas son las mismas,
     * entonces se recupera la nómina tal cual. En caso contrario,
     * se calcula, se almacena y se retorna. 
     */
    @Override
    public Nomina getNomina(int codEmp, Meses mes, int horasTrabajas) {
		Nomina nomina = NominaDao.getNomina(codEmp, mes);
		if (existeNomina(nomina) &&
			mismasHorasTrabajadas(nomina, horasTrabajas)) 
		{
			return nomina;		
		}
		return createAndStoreNomina(codEmp, mes, horasTrabajas);		
	}
    
    private boolean existeNomina(Nomina nomina) {
    	return nomina != null;
    }
    
    private boolean mismasHorasTrabajadas(Nomina nomina, int newHoras) {
    	int oldHoras = nomina.getHorasTrabajadas();
		return oldHoras == newHoras ? true : false;
	}
	
    /*
     * Se obtiene la nueva nomina calculada y se almacena
     */
	private Nomina createAndStoreNomina(int codEmp, Meses mes, int horasTrabajadas) {		
		Nomina nomina = calcularNomina(codEmp, mes, horasTrabajadas); // calcularla
		NominaDao.guardarNomina(codEmp, mes, nomina); // guardarla		
		return nomina;		
	}
	
	/*
	 * El cálculo de un nomina es algo realmente complejo,
	 * por lo que simplificamos tanto como nos apetezca. 
	 */
	private Nomina calcularNomina(int codEmp, Meses mes, 
			int horasTrabajadas) 
	{
		Empleado empleado = EmpleadoDao.getEmpleado(codEmp);
		
		float bruto = empleado.getSueldoBruto();
		float bruto_mes_teorico = bruto / 12; // 12 meses
		
		/*
		 * Calculamos el sueldo en bruto para el mes.
		 * El empleado tiene un sueldo en bruto mensual si ha trabajado 
		 * exactamente el numero de horas fijado por horasMensualesOficiales.
		 * Tenemos que mirar cuántas horas ha trabajado realmente para
		 * estimar su sueldo bruto mensual.
		 */
		float bruto_mes_calculado = 
			(bruto_mes_teorico * horasTrabajadas) / horasMensualesOficiales;
		
		// Descontamos la retencion IRPF
		float neto_mes = (1 - porcentRet/100f) * bruto_mes_calculado; 
		
		String observaciones = "Ha trabajado ";
		
		if (horasTrabajadas > horasMensualesOficiales) {
			observaciones += (horasTrabajadas-horasMensualesOficiales) + " horas mas ese mes";
		} else if (horasTrabajadas < horasMensualesOficiales) {
			observaciones += (horasMensualesOficiales-horasTrabajadas) + " horas menos ese mes.";
		} else {
			observaciones += " las horas normales: " + horasMensualesOficiales + ".";
		}
		
		Nomina nomina = new Nomina(codEmp, horasTrabajadas, mes, 
				bruto_mes_calculado, neto_mes, observaciones);		
		return nomina;		
	}

}