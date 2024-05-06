import java.util.ArrayList;
import java.util.List;

class Persoana{
    private String nume;
    private int varsta;

    public Persoana(String nume, int varsta){
        this.nume=nume;
        this.varsta=varsta;
    }
    public String getNume(){
        return nume;
    }
    public int getVarsta(){
        return varsta;
    }
    public void afiseazaDetalii(){
        System.out.println("Nume: " + nume + ", Varsta: " + varsta);
    }
    public void participaLaEveniment(EvenimentAcademic eveniment){
        System.out.println(getNume()+ " participa la evenimentul " + eveniment.getNume());
    }
    public void anuleazaParticiparea(EvenimentAcademic eveniment){
        System.out.println(getNume()+ " a anulat participarea la evenimentul " + eveniment.getNume());
    }
}

class Materie {
    private String nume;

    public Materie(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
}
class Nota{
        private double punctaj;
        private Materie materie;
        public Nota(double punctaj, Materie materie){
            if(punctaj <0 || punctaj >10){
                throw new IllegalArgumentException("Punctajul notei trebuie sa fie intre 0 si 10");
            }
            if(materie == null){
                throw new IllegalArgumentException("Materia notei nu poate fi nula");
            }
            this.punctaj=punctaj;
            this.materie = materie;
        }
        public double getPunctaj(){
            return punctaj;
        }
        public Materie getMaterie(){
            return materie;
        }
}
class EvenimentAcademic {
    protected String nume;
    protected String data;
    protected String locatie;

    public EvenimentAcademic(String nume, String data, String locatie) {
        if(nume ==null || nume.isEmpty() || data ==null || data.isEmpty() || locatie ==null || locatie.isEmpty()){
            throw new IllegalArgumentException("Datele introduse pentru evenimentul academic sunt invalide");
        }
        this.nume = nume;
        this.data = data;
        this.locatie = locatie;
    }

    public String getNume() {
        return nume;
    }

    public String getData() {
        return data;
    }

    public String getLocatie() {
        return locatie;
    }
}
class Examen extends EvenimentAcademic{
        private Materie materie;
        public Examen(String nume, String data, String locatie, Materie materie){
            super(nume,data,locatie);
            this.materie=materie;
        }
        public Materie getMaterie(){
            return materie;
        }
}
class Seminar extends EvenimentAcademic{
        private Profesor organizator;
        public Seminar (String nume, String data, String locatie, Profesor organizator){
            super(nume, data, locatie);
            this.organizator = organizator;
        }
        public Profesor getOrganizator(){
            return organizator;
        }
}

class Student extends Persoana {
    private String facultate;
    private List<Materie> materii;
    private List<Nota> note;

    public Student(String nume, int varsta, String facultate) {
        super(nume, varsta);
        this.facultate = facultate;
        this.materii = new ArrayList<>();
        this.note = new ArrayList<>();
    }

    public void adaugaMaterie(Materie materie) {
        materii.add(materie);
    }

    public void adaugaNota(Nota nota) {
        note.add(nota);
    }

    public void afiseazaNote() {
        System.out.println("Notele lui " + getNume() + ":");
        for (Nota nota : note) {
            System.out.println("Materie: " + nota.getMaterie().getNume() + ", Nota " + nota.getPunctaj());
        }
    }
    public List<Materie> getMaterii(){
        return materii;
    }
    public String getFaculate(){
        return facultate;
    }
    @Override
    public void afiseazaDetalii(){
        super.afiseazaDetalii();
        System.out.println("Facultate: " + facultate);
    }
    public double calculeazaMedia(){
        if(note.isEmpty()){
            System.out.println("Studentul " + getNume() + " nu are note.");
            return 0.0;
        }

        double sumaPunctaje = 0;
        for (Nota nota : note) {
            sumaPunctaje += nota.getPunctaj();
        }
        return sumaPunctaje / note.size();
    }
    public String getFacultate(){
        return facultate;
    }
}
class Profesor extends Persoana {
    private String disciplina;
    private List<Materie> materiiPredate;
    public Profesor(String nume, int varsta, String disciplina){
        super(nume, varsta);
        this.disciplina = disciplina;
        this.materiiPredate = new ArrayList<>();
    }
    public void adaugaMaterie(Materie materie){
        materiiPredate.add(materie);
    }
    public void afiseazaMateriiPredate(){
        System.out.println("Materii predate de " + getNume() + ":");
        for (Materie materie : materiiPredate){
            System.out.println(materie.getNume());
        }
    }
    private String getDisciplina(){
        return disciplina;
    }

    @Override
    public void afiseazaDetalii(){
        super.afiseazaDetalii();
        System.out.println("Disciplina: " + disciplina);
    }
}

class Facultate{
    private String nume;
    private List<Student> studenti;
    private List<Profesor> profesori;
    public Facultate(String nume){
        this.nume=nume;
        this.studenti=new ArrayList<>();
        this.profesori = new ArrayList<>();
    }
    public void adaugaStudent(Student student){
        studenti.add(student);
    }
    public void adaugaProfesor(Profesor profesor){
        profesori.add(profesor);
    }
    public void afiseazaStudenti(){
        System.out.println("Studenti la " + nume + ":");
        for (Student student : studenti){
            student.afiseazaDetalii();
        }
    }
    public void afiseazaProfesori(){
        System.out.println("Profesori la " + nume + ":");
        for (Profesor profesor : profesori){
            profesor.afiseazaDetalii();
        }
    }
}
class ContBancar{
    private Persoana titular;
    private double sold;

    public ContBancar(Persoana titular, double soldInitial){
        this.titular=titular;
        this.sold=soldInitial;
    }

    public void depune(double suma){
        sold += suma;
        System.out.println("Suma de " + suma +" RON a fost adaugata");
    }
    public void retrage(double suma) {
        if (suma <= sold) {
            sold -= suma;
            System.out.println("Suma de " + suma + " RON a fost retrasa din contul lui " + titular.getNume());
        } else {
            System.out.println("Fonduri insuficiente pentru retragere!");
        }
    }
    public void afiseazaSold(){
        System.out.println("Soldul actual contului lui " + titular.getNume() + " este: " + sold + " RON");
    }
}
public class Administrare{
    public static void main(String[] arags){
        Student student1=new Student("Madalin", 21, "Informatica");
        Profesor profesor1 = new Profesor("Ionel", 45, "Programare");
        Facultate facultate= new Facultate("Facultatea de Stiinte");
        Materie matematica = new Materie("Matematica");
        Materie programare=new Materie("Programare");
        Nota notaMatematica= new Nota(9.5, matematica);
        Nota notaProgramare = new Nota(8.75, programare);
        Examen examenMatematica = new Examen("Examen de matematica", "10/05/2024", "Sala 101", matematica);
        Seminar seminarProgramare = new Seminar("Seminariu de programare", "15/05/2024", "Laboratorul 2", profesor1);
        student1.adaugaMaterie(matematica);
        student1.adaugaMaterie(programare);
        student1.adaugaNota(notaMatematica);
        student1.adaugaNota(notaProgramare);
        profesor1.adaugaMaterie(programare);

        student1.participaLaEveniment(examenMatematica);
        profesor1.participaLaEveniment(seminarProgramare);

        System.out.println("Materiile lui " + student1.getNume() + ":");
        for (Materie materie : student1.getMaterii()){
            System.out.println(materie.getNume());
        }
        student1.afiseazaNote();
        System.out.println("Facultatea lui " + student1.getNume() + " este " + student1.getFacultate());
        facultate.adaugaStudent(student1);
        facultate.adaugaProfesor(profesor1);
        System.out.println("Studenti:");
        facultate.afiseazaStudenti();
        System.out.println("\nProfesori:");
        facultate.afiseazaProfesori();
        Persoana titularCont= new Persoana("Madalin", 21);
        ContBancar contMadalin=new ContBancar(titularCont,10);
        contMadalin.afiseazaSold();
        contMadalin.depune(500);
        contMadalin.afiseazaSold();
        contMadalin.retrage(200);
        contMadalin.afiseazaSold();
    }
}