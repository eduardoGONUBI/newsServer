package com.example.demo;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller // This means that this class is a Controller
public class MainController {

	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NoticiaRepository noticiaRepository;
	


@GetMapping(path="/")
	public String getAllNoticias (Model model){
		model.addAttribute("ListaNoticias",  noticiaRepository.findAll());
		return "Main";
	}

@GetMapping(path="/index_User")
public String getAllU (Model model){
	model.addAttribute("ListaNoticias",  noticiaRepository.findAll());
	return "index_User";
}

@GetMapping("/Lista_TN/{id}")
public String Lista_TN(@PathVariable(value = "id") Integer id,Model model) {
	ArrayList<Noticia> Lista = new ArrayList<>();
	ArrayList<Noticia> r= new ArrayList<>();
	noticiaRepository.findAll().forEach(Lista::add);
	for(int i=0;i<Lista.size();i++) {
		if(Lista.get(i).getIdTopico().equals(id)){
			r.add(Lista.get(i));
		}
	}
	model.addAttribute("ListaNoticiasTopico",  r.clone());
 return "Lista";
}


@GetMapping(path="/index_Publisher")
public String getAll(Model model){
	model.addAttribute("ListaNoticias",  noticiaRepository.findAll());
	model.addAttribute("ListaTopico",  topicoRepository.findAll());
	return "index_Publisher";
}






@GetMapping(path="/Sucesso")
public String load(){
	return "Sucesso";
}

@GetMapping(path="/Falhou")
public String load2(){
	return "Falhou";
}

@GetMapping("/delete_User/{id}")
public String deleteUser(@PathVariable(value = "id") Integer id) {
	userRepository.deleteById(id);
 return "redirect:/";
}

@GetMapping("/delete_Topico/{id}")
public String delete_Topico(@PathVariable(value = "id") Integer id) {
	ArrayList<Noticia>Lista= new ArrayList<>();
	noticiaRepository.findAll().forEach(Lista::add);
	for(int i=0; i<Lista.size();i++){
		Integer x= new Integer(Lista.get(i).getIdTopico());
		if(id.equals(x)){
			noticiaRepository.deleteById(Lista.get(i).getId());
		}
	}
	topicoRepository.deleteById(id);
 return "redirect:/index_Publisher";
}

@GetMapping("/delete_Noticia/{id}")
public String deleteNoticia(@PathVariable(value = "id") Integer id) {
	noticiaRepository.deleteById(id);
 return "redirect:/index_Publisher";
}


@GetMapping("/Adicionar_Noticias")
public String addNoticia(Model model) {
 Noticia n= new Noticia();
 model.addAttribute("Adicionar_Noticias",n );
 return "Adicionar_Noticias";
}

@PostMapping("/Save_Noticia")
public String saveNoticia(@ModelAttribute("Adicionar_Noticias") Noticia n ) {
	ArrayList <Topico>Lista_Topicos= new ArrayList<>();
	topicoRepository.findAll().forEach(Lista_Topicos::add);
	boolean achou=false;
	Integer x= new Integer(n.getIdTopico());
	
	for(int i=0;i<Lista_Topicos.size();i++){
		Integer y= new Integer(Lista_Topicos.get(i).getId());
		if(x.equals(y)){
			n.setTopico(Lista_Topicos.get(i).getTopico());
			achou=true;
		}
	}
	if(achou) {
		noticiaRepository.save(n);
		return "redirect:/Sucesso";
	}
	return "redirect:/Falhou";
}


@GetMapping("/Adicionar_Topico")
public String addTopico(Model model) {
 Topico t = new Topico();
 model.addAttribute("Adicionar_Topico", t);
 return "Adicionar_Topico";
}

@PostMapping("/Save_Topico")
public String saveTopico(@ModelAttribute("Adicionar_Topico") Topico t ) {
	ArrayList <Topico>Lista_Topicos= new ArrayList<>();
	topicoRepository.findAll().forEach(Lista_Topicos::add);
	boolean achou=false;
	for(int i=0;i<Lista_Topicos.size();i++){
		if(Lista_Topicos.get(i).getTopico().equals(t.getTopico())){
			achou=true;
		}
	}
	if(!achou) {
		topicoRepository.save(t);
		return "redirect:/Sucesso";
	}
	return "redirect:/Falhou";
}


@GetMapping("/Registar_User")
public String showRegistar(Model model) {
 User u = new User();
 model.addAttribute("Registar_User", u);
 return "Registar_User";
}

@PostMapping("/Save_User")
public String saveUser (@ModelAttribute("Registar_User") User u ) {
	userRepository.save(u);
 return "redirect:/";
}


@GetMapping("/showLogin")
public String showLogin(Model model) {
	 User u = new User();
	 model.addAttribute("Registar_User", u);
	 return "Login";
}

@PostMapping("/L_User")
public String Loginconfirm (@ModelAttribute("Registar_User") User u ) {
	ArrayList<User>Lista = new ArrayList<>();
	userRepository.findAll().forEach(Lista::add);
	for(int i=0;i<Lista.size();i++){
		System.out.print(Lista.get(i));
		if(Lista.get(i).getNome().equals(u.getNome())&& Lista.get(i).getPassword().equals(u.getPassword())){
			if(Lista.get(i).getPublisher()){
				return "redirect:/index_Publisher";
			}else{
				return "redirect:/index_User";
			}
		}
	}
 return "redirect:/";
}


@GetMapping("/Update_Topico/{id}")
public String Update_Topico(@PathVariable(value = "id") Integer id,Model model) {
Optional < Topico > optional = topicoRepository.findById(id);
Topico t = new Topico();
 if (optional.isPresent()) {
  t.setId(id);
  t.setTopico(optional.get().getTopico());
 } 
 model.addAttribute("Topico", t);
 return "Update_Topico";
}



@PostMapping("/up_Topico")
public String up_Topico(@ModelAttribute("Update_Topico")Topico t ) {
	ArrayList <Topico>Lista_Topicos= new ArrayList<>();
	ArrayList <Noticia>Lista_Noticias= new ArrayList<>();
	topicoRepository.findAll().forEach(Lista_Topicos::add);
	noticiaRepository.findAll().forEach(Lista_Noticias::add);
	boolean achou=false;
	Integer x= new Integer(t.getId());
	for(int i=0;i<Lista_Topicos.size();i++){
		Integer y=new Integer(Lista_Topicos.get(i).getId());	
		
		if(y.equals(x)){
			Optional<Topico> ts= topicoRepository.findById(y);
			ts.get().setId(x);
			
			ts.get().setTopico(t.getTopico());			
			for(int o=0;o<Lista_Noticias.size();o++){
				Integer z= new Integer(Lista_Noticias.get(o).getIdTopico());
				if(z.equals(ts.get().getId())){
					System.out.println(o);
					Lista_Noticias.get(o).setIdTopico(ts.get().getId());
					Lista_Noticias.get(o).setTopico(ts.get().getTopico());		
				}
			}
			noticiaRepository.deleteAll();
			noticiaRepository.saveAll(Lista_Noticias);
			return "redirect:/Sucesso";
		}
	}
	
	return "redirect:/Falhou";
}

@GetMapping("/Update_Noticia/{id}")
public String Update_Noticia(@PathVariable(value = "id") Integer id,Model model) {
Optional < Noticia > optional = noticiaRepository.findById(id);
Noticia n =null;
 if (optional.isPresent()) {
	 n=optional.get();
 } 
 model.addAttribute("Noticia", n);
 return "Update_Noticia";
}



@PostMapping("/up_Noticia")
public String up_Noticia(@ModelAttribute("Update_Noticia")Noticia n) {
	ArrayList <Noticia>Lista_Noticias= new ArrayList<>();
	noticiaRepository.findAll().forEach(Lista_Noticias::add);
	boolean achou=false;
	Integer x= new Integer(n.getId());
	for(int i=0;i<Lista_Noticias.size();i++){
		Integer y= new Integer(Lista_Noticias.get(i).getId());
		if(y.equals(x)){
			Optional<Noticia> nn= noticiaRepository.findById(y);
			nn.get().setId(x);
			nn.get().setIdTopico(n.getIdTopico());
			nn.get().setNoticia(n.getNoticia());
			nn.get().setTopico(topicoRepository.findById(n.getIdTopico()).get().getTopico());
			noticiaRepository.deleteAll();
			noticiaRepository.saveAll(Lista_Noticias);
			return "redirect:/Sucesso";
		}
	}
	noticiaRepository.deleteAll();
	noticiaRepository.saveAll(Lista_Noticias);
	return "redirect:/Falhou";
}


}
