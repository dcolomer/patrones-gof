package estructurales.proxy.protection_proxy.modelo;

import estructurales.proxy.protection_proxy.dao.EmpleadoDao;

public class Nomina {
	
	private int codEmp, horasTrabajadas;
	private Meses mes;
	private float bruto, neto;
	private String observaciones;
	
	public Nomina(int codEmp, int horasTrabajadas, Meses mes, float bruto, 
			float neto, String observaciones) 
	{
		this.codEmp = codEmp;
		this.horasTrabajadas = horasTrabajadas; 
		this.mes = mes;
		this.bruto = bruto;
		this.neto = neto;		
		this.observaciones = observaciones;
	}
	
	@Override
	public String toString() {		
		Empleado empleado = EmpleadoDao.getEmpleado(codEmp);
		String parteEmpleado = empleado.toString();
		
		String parteNomina = "Nomina [mes=" + mes + ", horas=" + 
		horasTrabajadas	+ ", bruto=" + bruto + ", neto=" + neto + "]";
		
		String parteObs = observaciones != "" ? 
				"Observaciones=" + observaciones : "";
		
		return parteEmpleado + "\n" + parteNomina + "\n" + parteObs + "\n" +
			"----------------------------------------------------------------";			
	}

	public int getHorasTrabajadas() { return horasTrabajadas; }
	public int getCodEmp() { return codEmp;	}
	public Meses getMes() { return mes;	}
	public float getBruto() { return bruto;	}
	public float getNeto() { return neto; }
	public String getObservaciones() { return observaciones; }
	
}
