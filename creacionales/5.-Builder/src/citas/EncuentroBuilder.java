package creacionales.builder.citas;

import creacionales.builder.citas.beans.Cita;

public class EncuentroBuilder extends CitaBuilder {
	
	@Override
	public Cita getCita() throws FaltaInfoException {
		try {
			super.getCita();
		} finally {
			if (cita.getFechaFin() == null) {
				elementosRequeridos += FECHA_FIN_REQUERIDA;
			}

			if (elementosRequeridos > 0) {
				throw new FaltaInfoException(elementosRequeridos);
			}
		}
		return cita;
	}
}