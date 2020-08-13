package com.AI.AIPrzyWyborzeJezyka.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AI.AIPrzyWyborzeJezyka.Data.ListaJezykow;
import com.AI.AIPrzyWyborzeJezyka.Data.Odpowiedzi;
import com.AI.AIPrzyWyborzeJezyka.Data.ZapytaniaJarvis;
import com.AI.AIPrzyWyborzeJezyka.Neuron.Data;
import com.AI.AIPrzyWyborzeJezyka.Neuron.Neuron;

import org.springframework.ui.Model;

@Controller
public class DomowaController {
	public static int n = 0;
	public static List<Data> dane = new ArrayList<Data>();
	public int a = 0, b = 0, c = 0, d = 0; // odpowiedzi danego użytkownika
	public static int czyPomoglem; // odpowiedz na koniec czy byla pomocna, w przeciwnym wypadku AI nie uczy się na
									// niej

	int wynik;

	@GetMapping("/domowaAI")
//	@ResponseBody
	public String stronaDomowa() {
		a = 0;
		b = 0;
		c = 0;
		d = 0;
		Odpowiedzi.daneOdp2.clear();
		Odpowiedzi.daneOdp.clear();
//		dane.clear();		
		System.out.println("LISTY WYCZYSZCZONE");
		n = 0;

		return "domowaAI";
	}

	
	// ----- Nawigacja JARVIS -----
	
	
	
	@GetMapping("/JARVISchat")
	public String JARVISchat(@ModelAttribute Odpowiedzi odp, ZapytaniaJarvis pyt, Model model) {

		model.addAttribute("odpowiedz", odp);
		model.addAttribute("zapytanie", pyt);
		n = 0;
		ZapytaniaJarvis.y = ZapytaniaJarvis.danePytan.get(0);
		Odpowiedzi.daneOdp2.add(odp.getOdp());
		
		Odpowiedzi.imie = Odpowiedzi.daneOdp2.get(0);
		n++;

		return "JARVISchat";
	}

	@PostMapping("/JARVISchat")
	public String setPerson(@ModelAttribute Odpowiedzi odp, ZapytaniaJarvis pyt, Model model) {

		model.addAttribute("odpowiedz", odp);
		model.addAttribute("zapytanie", pyt);

		ZapytaniaJarvis.y = ZapytaniaJarvis.danePytan.get(n);
		Odpowiedzi.daneOdp2.add(odp.getOdp());

		System.out.println(n + ": " + Odpowiedzi.daneOdp2);
		Odpowiedzi.imie = Odpowiedzi.daneOdp2.get(0);
		Odpowiedzi.x = Odpowiedzi.daneOdp2.get(n);

		System.out.println(ZapytaniaJarvis.danePytan.size() + " <--rozmiar pytan n=" + n);

		dane.add(new Data(Arrays.asList(new Integer[] { 1, 1, 1, 1 }), 1));
		dane.add(new Data(Arrays.asList(new Integer[] { 2, 2, 2, 2 }), 1));
		dane.add(new Data(Arrays.asList(new Integer[] { 0, 5, 0, 1 }), 1));
		dane.add(new Data(Arrays.asList(new Integer[] { 1, 0, 1, 1 }), 0));
		dane.add(new Data(Arrays.asList(new Integer[] { 0, 0, 0, 0 }), 0));
		dane.add(new Data(Arrays.asList(new Integer[] { 1, 2, 2, 1 }), 0));
		dane.add(new Data(Arrays.asList(new Integer[] { 0, 0, 0, 4 }), 1));

//		dane.add(new Data(Arrays.asList(new Integer[] { a, b, c, d }), czyPomoglem));

		Neuron neuron = new Neuron();
		neuron.zaladujDane(dane);

		do {
			neuron.uczSie();
		} while (Neuron.czyWymagaPowtorki);

		
			if (n == 1 && (Odpowiedzi.daneOdp2.get(n).equals("1")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("tak"))) {
				czyPomoglem = 1;
			} 
			if (n == 1 && (Odpowiedzi.daneOdp2.get(n).equals("2")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("nie"))) {
				czyPomoglem = 0;
			}
		
		dane.add(new Data(Arrays.asList(new Integer[] { a, b, c, d }), czyPomoglem));
			

//		dane.add(new Data(Arrays.asList(new Integer[] { a, b, c, d }), czyPomoglem));

		if (n == 2) {

			if (n == 2 && (Odpowiedzi.daneOdp2.get(n).equals("1")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("gry"))) {
				a += 4;
			}
			if (n == 2 && (Odpowiedzi.daneOdp2.get(n).equals("2")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("web"))) {
				b += 1;
			}
			if (n == 2 && (Odpowiedzi.daneOdp2.get(n).equals("3")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("mobile"))) {
				c += 1;
			}
			if (n == 2 && (Odpowiedzi.daneOdp2.get(n).equals("4")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("analiza"))) {
				d += 3;
			}
		}

		if (n == 3) {
			if (n == 3 && (Odpowiedzi.daneOdp2.get(n).equals("1")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("windows")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("konsole")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("win"))) {
				a += 1;
				d += 1;
			}
			if (n == 3
					&& (Odpowiedzi.daneOdp2.get(n).equals("2") || Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("mac")
							|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("macos")
							|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("macbook"))) {
				b += 1;
				d += 1;
			}
			if (n == 3 && (Odpowiedzi.daneOdp2.get(n).equals("3")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("mobile")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("android"))) {
				c += 1;
				d += 1;
			}
		}

		if (n == 4) {
			if (n == 4 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("widzę efekt")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("efekt")
					|| Odpowiedzi.daneOdp2.get(n).equals("1"))) {
				b += 1;
			}
			if (n == 4 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("logika")
					|| Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("logika dzialania")
					|| Odpowiedzi.daneOdp2.get(n).equals("2"))) {
				c += 1;
			}
		}

		if (n == 5) {
			if (n == 5 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("słabo")
					|| (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("1"))
					|| (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("zle")))) {
				b += 3;
			}
			if (n == 5 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("średnio")
					|| (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("2")))) {
				a += 2;
			}
			if (n == 5 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("dobrze")
					|| (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("3")))) {
				d += 2;
				c += 4;
			}
		}
//
//	if(n==6) {
//		if ( n==5 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("tak") || (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("1")))) {
//			czyPomoglem=1;
//		}
//		else if (n==5 && (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("nie") || (Odpowiedzi.daneOdp2.get(n).toLowerCase().equals("2")))) {
//			czyPomoglem=0;
//		}
//		
//	}

//		if (true) {
//		dane.add(new Data(Arrays.asList(new Integer[] { a, b, c, d }), czyPomoglem));
//		}

//		dane.add(new Data(Arrays.asList(new Integer[] { 1, 10, 100, 1 }), 1));

		if (n == 6) {
			
			a*=neuron.getRESULT().get(0);
			b*=neuron.getRESULT().get(1);
			c*=neuron.getRESULT().get(2);
			d*=neuron.getRESULT().get(3);

			System.out.println("a:" + a + "; b: "+ b + "; c:" + c + "; d:" + d);
			
			if (a > b && a > c && a > d) {
				System.out.println("C++");
				return "C++";
			}
			if (b > a && b > c && b > d) {
				System.out.println("HTML");
				return "HTML";
			}
			if (c > b && c > a && c > d) {
				System.out.println("JAVA");
				return "JAVA";
			}
			if (d > b && d > c && d > a) {
				System.out.println("SQL");
				return "SQL";
			}
		}

		System.out.println("Odpowiedzi: " + dane + "\n");
		System.out.println("Stare Wagi:         " + neuron.wagi);
		System.out.println("Zaktualizowane Wagi " + neuron.getRESULT());
		System.out.println("Dane wejsciowe      " + Data.data);
		System.out.println(Odpowiedzi.daneOdp2);
		n++;

//		
//		if (ZapytaniaJarvis.danePytan.size()==n){
//			System.out.println("Koniec pytań");
//			return "OdpJARVIS";
//		}		
//		else
		return "JARVISchat";

	}

	@GetMapping("/LoginForm")
	public String LoginForm(Model model) {
		a = 0;
		b = 0;
		c = 0;
		d = 0;
		Odpowiedzi.daneOdp2.clear();
		Odpowiedzi.daneOdp.clear();
//		dane.clear();
		System.out.println("LISTY WYCZYSZCZONE");
		n = 0;

		model.addAttribute("odpowiedz", new Odpowiedzi());
		model.addAttribute("pytanie", new ZapytaniaJarvis());

		return "LoginForm";
	}

	@PostMapping("/LoginForm")
	public String getPerson(@ModelAttribute Odpowiedzi odp, ZapytaniaJarvis pyt, Model model) {

		model.addAttribute("odpowiedz", odp);
		model.addAttribute("zapytanie", pyt);

		return "LoginForm";
	}
	

	@GetMapping("/OdpJARVIS")
	public String OdpJARVIS() {
		dane.clear();
//		Odpowiedzi.imie=Odpowiedzi.getImie();
		Odpowiedzi.daneOdp2.clear();
		Odpowiedzi.daneOdp.clear();
		n=1;
		for (int i=0; i < Odpowiedzi.daneOdp2.size(); i++)
		{
			dane.remove(i);
		}
		System.out.println(Odpowiedzi.daneOdp2);
		return "JARVISchat";
	}

	
	@GetMapping("/JezykiProg")
	public String jezykiProgramowania() {

		return "JezykiProg";
	}
	
	
	
// Nawigacja VERONICA
	
	@GetMapping("/Veronica")
	public String getVeronica() {
		return "Veronica";
	}
	
	@GetMapping("/programista")
	public String getProgramista() {
		return "programista";
	}
	
	@GetMapping("/projekt")
	public String getProjekt() {
		return "projekt";
	}
	
	@GetMapping("/gry")
	public String getGry() {
		return "gry";
	}
	
	@GetMapping("/gry2")
	public String getGry2(Model model) {
		model.addAttribute("listaJezykowGry", ListaJezykow.getListaJezykowGry());
		return "gry2";
	}
	
	@GetMapping("/gry3")
	public String getGry3(Model model) {
		model.addAttribute("listaJezykowGry2", ListaJezykow.getListaJezykowGry2());
		return "gry3";
	}
	
	@GetMapping("/aplikacje")
	public String getAplikacje() {
		return "aplikacje";
	}
	
	@GetMapping("/aplikacje2")
	public String getAplikacje2() {
		return "aplikacje2";
	}
	
	@GetMapping("/aplikacje3")
	public String getAplikacje3() {
		return "aplikacje3";
	}
	
	@GetMapping("/aplikacje4")
	public String getAplikacje4() {
		return "aplikacje4";
	}
	
	@GetMapping("/iOS")
	public String getIOS(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(4));
		return "iOS";
	}
	
	@GetMapping("/JavaV")
	public String getJava(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(5));
		return "JavaV";
	}
	
	@GetMapping("/Windows")
	public String getWindows(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(6));
		return "Windows";
	}
	
	@GetMapping("/dataScience")
	public String getDataScience(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow());
		return "dataScience";
	}
	
	@GetMapping("/machineLearning")
	public String getMachineLearning(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(1));
		return "machineLearning";
	}
	
	@GetMapping("/stronaInt")
	public String getStronaInt(Model model) {
		model.addAttribute("listaJezykowFront", ListaJezykow.getListaJezykowFront());
		return "stronaInt";
	}
	
	@GetMapping("/backend")
	public String getBackend(Model model) {
		model.addAttribute("listaJezykowBack", ListaJezykow.getListaJezykowBack());
		return "backend";
	}
	
	@GetMapping("/bazyDanych")
	public String getBazyDanych(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(3));
		return "bazyDanych";
	}
	
	@GetMapping("/propozycja")
	public String getPropozycja(Model model) {
		model.addAttribute("listaJezykow", ListaJezykow.getListaJezykow().get(1));
		return "propozycja";
	}
	
	@GetMapping("/dlaczego")
	public String getDlaczego() {
		return "dlaczego";
	}

	
}
