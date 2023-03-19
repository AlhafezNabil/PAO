package main.java.curs4;

// clasa record nu poate fi extinsa

// se creeaza automat o clasa imutabila cu aceleasi campuri private, getter + setter

/*
criterii :
- sa nu aiba fctie set
- datele sa fie private
- clasa sa fie finala (nu se poate extinde)
- compozitie pentru referinte catre ob mutabile
- nu punem referinte catre campuri mutabile, ci copii ale lor (in getter)
 */
public class clase_imutabile {
    // clase de inregistare : compilatorul genereaza o clasa imutabila pt datele de inregistrare

    public static void main(String[] args) {
        Student s = new Student("Popa Ion", 241, 9.5);
        System.out.println(s.name());
    }
}