package me.abeilles.chess.bll.inscription;

public interface InscriptionService {
    void inscriptionTournoi(Integer id, String pseudo);
    void supprimerInscriptionTournoi(Integer id, String pseudo);
}
