package net.studyprojects.financas.mb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MenuBean {

	public String contas() {
		return "contas";
	}

	public String pagadores() {
		return "pagadores";
	}

	public String pagamentos() {
		return "pagamentos";
	}

}
