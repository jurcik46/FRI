package pkg2_semestralna_praca_do_filip_kubala;

/**
 * @author Filip Kubala
 */
class Prvok {
    private final int aHmotnost;
    private final int aCena;
    private final float aPomerCenaHmotnost;
    private boolean aPatriDoBatohu;

    Prvok(int paHmotnost, int paCena) {
        aHmotnost = paHmotnost;
        aCena = paCena;
        aPomerCenaHmotnost = aCena / aHmotnost;
        aPatriDoBatohu = true; // vychodiskove riesenie je nepripustne tj. kazdy jeden prvok patri do batohu
    }
    
    public int dajHmotnostPrvku(){
        return aHmotnost;
    }
    
    public int dajCenuPrvku(){
        return aCena;
    }
    
    public float dajPomerCenaHmotnostPrvku(){
        return aPomerCenaHmotnost;
    }
    
    public boolean patriPrvokDoBatohu(){
        return aPatriDoBatohu;
    }
    
    public void vyberPrvokZBatohu(){
        aPatriDoBatohu = false;
    }
    
    public void vlozPrvokDoBatohu(){
        aPatriDoBatohu = true;
    }
    
    @Override
    public String toString(){
        return "Hmotnosť: " + aHmotnost + " ■ Cena: " + aCena +  " ■ Pomer ceny a hmotnosti: " + aPomerCenaHmotnost;
    }
}