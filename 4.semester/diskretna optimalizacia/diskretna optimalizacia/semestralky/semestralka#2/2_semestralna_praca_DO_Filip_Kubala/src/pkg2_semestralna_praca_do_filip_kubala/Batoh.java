package pkg2_semestralna_praca_do_filip_kubala;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Filip Kubala
 */
final class Batoh {
    private final int[] aVysledneRiesenie;              //vektor pozostávajúci z 0 a 1 na n-tom mieste predstavuje ci sa n-ty predmet nachadza v batohu
    private final Prvok[] aPrvky;                       //samotné pole prvkov
    private final int aKapacitaBatohu;                  //K=15000
    private final int aCelkovyPocPrvkov;                //n=500
    private final int aMaxPocPrvkovVBatohu;             //r=300
    private int aAktualHmotPrvkovVBatohu = 0;           //kolko majú prvky v batohu aktuálnu hmotnosť
    private int aAktualCenaPrvkovVBatohu = 0;           //hodnota účelovej funkcie
    private int aAktualPocPrvkovVBatohu = 0;            //kolko prvkov v batohu aktualne mám
    private int aPrevis;                                //Previs nad maximálnou kapacitou batohu

    Batoh(int paCelkovyPocetPrvkov, int paMaxPocetPrvkovVBatohu, int paKapacitaBatohu) {
        aPrvky = new Prvok[paCelkovyPocetPrvkov];
        nacitaj(paCelkovyPocetPrvkov);
        aVysledneRiesenie = new int[paCelkovyPocetPrvkov];
        aKapacitaBatohu = paKapacitaBatohu;
        aPrevis = aAktualHmotPrvkovVBatohu - aKapacitaBatohu;
        aCelkovyPocPrvkov = paCelkovyPocetPrvkov;
        aMaxPocPrvkovVBatohu = paMaxPocetPrvkovVBatohu;
        
        for(int i = 0; i < paCelkovyPocetPrvkov; i++){
            aVysledneRiesenie[i] = 1; //zaciatocne nepripustne riesenie kedy je v batohu kazdy predmet
            aAktualHmotPrvkovVBatohu += aPrvky[i].dajHmotnostPrvku();
            aAktualCenaPrvkovVBatohu += aPrvky[i].dajCenuPrvku();
            aAktualPocPrvkovVBatohu++;
        }
    }
    
    public int dajAktualHmotPrvkovVBatohu(){
        return aAktualHmotPrvkovVBatohu;
    }
    
    /**
     * @return hodnota ucelovej funkcie
     */
    public int dajAktualCenaPrvkovVBatohu(){
        return aAktualCenaPrvkovVBatohu;
    }
    
    /**
     * @return kolko prvkov je v batohu
     */
    public int dajAktualPocPrvkovVBatohu(){
        return aAktualPocPrvkovVBatohu;
    }
    
    /**
     * @return hodnota previsu
     */
    public int dajPrevis(){
        return aPrevis;
    }
    
    /**
     * naplni pole aPrvky zo suborov
     * @param paPocetPrvkov kolko riadkov ma kazdy subor; subory musia mat rovnaky pocet riadkov
     */
    public void nacitaj (int paPocetPrvkov) {
        File suborHmotnost = new File("data\\H6_a.txt");
        File suborCena = new File("data\\H6_c.txt");
        int nacitanaHmotnost;
        int nacitanaCena;
        
        try(Scanner nacitavacHmotnosti = new Scanner(suborHmotnost)){
            try(Scanner nacitavacCeny = new Scanner(suborCena)) {
                for(int i = 0; i < paPocetPrvkov; i++){
                    nacitanaHmotnost = nacitavacHmotnosti.nextInt();
                    nacitanaCena = nacitavacCeny.nextInt();
                    aPrvky[i] = new Prvok(nacitanaHmotnost, nacitanaCena);
                }
                nacitavacCeny.close();
            }catch(FileNotFoundException ex){
                System.out.println("Súbor s cenami prvkov sa nepodarilo nájsť!");
            }
            nacitavacHmotnosti.close();
        }catch(FileNotFoundException ex){
            System.out.println("Súbor s hmotnosťami prvkov sa nepodarilo nájsť!");
        }
    }
    
    /**
     * @return index prvku s najmensim pomerom cena/hmotnost pre Last Admissible
     */
    private int dajIndexPrvkuSNajmensimPomeromLA(){
        float min = 100000;
        //uchovávanie doteraz najmenšieho
        //nájdeného minima pomerov z prvkov v batohu
        
        int indexPrvku = 0;
        //pamätá si index alebo pozíciu prvku ktorý má
        //najmenší pomer z doteraz neodstránených prvkov z batoha
        
        for(int i = 0; i < aCelkovyPocPrvkov;i++){
            if(aVysledneRiesenie[i] == 1){
                if(aPrvky[i].dajPomerCenaHmotnostPrvku() <= min){
                    min = aPrvky[i].dajPomerCenaHmotnostPrvku();
                    indexPrvku = i;
                }
            }
        }
        return indexPrvku;
    }
    
    /**
     * @return index prvku s najmensim pomerom cena/hmotnost pre First Admissible
     */
    private int dajIndexPrvkuSNajmensimPomeromFA(){
        float min = 100000;
        int indexPrvku = 0;
        for(int i = 0; i < aCelkovyPocPrvkov;i++){
            if(aVysledneRiesenie[i] == 1){
                if(aPrvky[i].dajPomerCenaHmotnostPrvku() < min){
                    min = aPrvky[i].dajPomerCenaHmotnostPrvku();
                    indexPrvku = i;
                }
            }
        }
        return indexPrvku;
    }
    
    /**
     * kontroluje, ci su splnene vsetky strukturalne podmienky v zadani
     * @return splnene/nesplnene
     */
    private boolean splnaStruktPodmienky(){
        return ( (aAktualPocPrvkovVBatohu <= aMaxPocPrvkovVBatohu) && (aAktualHmotPrvkovVBatohu <= aKapacitaBatohu) );
    }
    
    /**
     * Dualna heuristika s vyhodnostnymi koeficientmi
     * Last Admissible 
     * @return vysledny vektor rieseni
     */
    public int[] vypocitajPoA() {
        while(!splnaStruktPodmienky() || aPrevis > 0){
            int vyhodeny = dajIndexPrvkuSNajmensimPomeromLA();
            dokonciVypocet(vyhodeny);
        }
        return aVysledneRiesenie;
    }
    
    /**
     * Dualna heuristika s vyhodnostnymi koeficientmi
     * First Admissible 
     * @return vysledny vektor rieseni
     */
    public int[] vypocitajPoB() {
        while(!splnaStruktPodmienky() || aPrevis > 0){
            int vyhodeny = dajIndexPrvkuSNajmensimPomeromFA();
            dokonciVypocet(vyhodeny);
        }
        return aVysledneRiesenie;
    }
    
    /**
     * aktualizuje udaje o zaradenych prvkoch v batohu
     * @param vyhadzovany index vyhadzovaneho prvku
     */
    private void dokonciVypocet(int vyhadzovany){
        aVysledneRiesenie[vyhadzovany] = 0;
        aAktualHmotPrvkovVBatohu -= aPrvky[vyhadzovany].dajHmotnostPrvku();
        aAktualCenaPrvkovVBatohu -= aPrvky[vyhadzovany].dajCenuPrvku();
        aAktualPocPrvkovVBatohu--;
        aPrevis = aAktualHmotPrvkovVBatohu - aKapacitaBatohu;
    }
    
    public String dajStart(){
        String riesenie = "";
        //vypis vysledny vektor
        for (int i = 0; i < aMaxPocPrvkovVBatohu; i++) {
            riesenie += aVysledneRiesenie[i] + " ";
        }
        riesenie += "\n";
        
        //vypis previs, volnu kapacitu a hodnotu ucelovej funkcie
        riesenie += "Hodnota ucelovej funkcie = " + dajAktualCenaPrvkovVBatohu() + "\n";
        riesenie += "Pocet prvkov v batohu = " + dajAktualPocPrvkovVBatohu() + "\n";
        riesenie += "Hmotnost batoha = " + dajAktualHmotPrvkovVBatohu() + "\n";
        riesenie += "Previs = " + dajPrevis() +"\n";
        riesenie += "Volna kapacita = " + (-dajPrevis() ) + "\n\n";
        return riesenie;
    }
}