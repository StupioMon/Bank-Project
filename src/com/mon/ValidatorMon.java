package com.mon;

public class ValidatorMon {

    public static void main(String[] args) {

    }

    public int isValidAmount(String amount){
        char[] chars = amount.toCharArray();
        int isValid=1;

        for(char c : chars){
            if(!Character.isDigit(c)){
                isValid=0;
                break;
            }
        }
        return isValid;

    }

    public int isValidName(String name){
        char[] chars = name.toCharArray();
        int isValid=1;

        for(char c : chars){
            if(Character.isDigit(c)){
                isValid=0;
                break;
            }
        }
        return isValid;

    }

    public int isValidContact(String contact){
        char[] chars = contact.toCharArray();
        int isValid=1;


        if(contact.length()==11){
            for(char c : chars){
                if(!Character.isDigit(c)){
                    isValid=0;
                    break;
                }
            }

        }
        else {
            isValid=0;
        }


        return isValid;

    }

    public int isValidEmail(String email){
        char[] chars = email.toCharArray();
        String mailpattern="";
        int isValid=0;
        int checkFirst=0;

        for(char c : chars){
            if(checkFirst==0){
                if(Character.isDigit(c)){
                    isValid=0;
                    break;
                }
                checkFirst++;
            }
            if(c=='.' || c=='@'){
                mailpattern+=Character.valueOf(c);
            }

        }

        if(mailpattern.equals("@.") || mailpattern.equals(".@.") || mailpattern.equals("@..")){
            isValid =1;
        }

        return isValid;

    }
}
