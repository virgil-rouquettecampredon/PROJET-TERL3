package org.example.model.EquationDeDeplacement;

public abstract class MauvaiseImplementationEquationDeDeplacementException extends Exception{
    private String message;

    public MauvaiseImplementationEquationDeDeplacementException(){
        super();
    }

    public MauvaiseImplementationEquationDeDeplacementException(String s){
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
