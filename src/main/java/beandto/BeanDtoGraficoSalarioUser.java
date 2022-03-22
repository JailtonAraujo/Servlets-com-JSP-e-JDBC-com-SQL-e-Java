package beandto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanDtoGraficoSalarioUser implements Serializable {

	private static final long serialVersionUID = 1L;

	List<String> listPerfil = new ArrayList<String>();
	List<Double> listMediaSalarial = new ArrayList<Double>();

	public List<String> getListPerfil() {
		return listPerfil;
	}

	public void setListPerfil(List<String> listPerfil) {
		this.listPerfil = listPerfil;
	}

	public List<Double> getListMediaSalarial() {
		return listMediaSalarial;
	}

	public void setListMediaSalarial(List<Double> listMediaSalarial) {
		this.listMediaSalarial = listMediaSalarial;
	}

}
