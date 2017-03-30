package eanCheck.bl;

import eanCheck.enumeration.CodeType;

public class BarcodeCheckDigitCalculator {

    private CodeType codeType;
    private int oddWeight;
    private int evenWeight;
    private int completetCodeLength;

    public BarcodeCheckDigitCalculator(CodeType codeType) throws Exception {
        this.codeType = codeType;
        oddWeight = 1;
        evenWeight = 3;
        switch (codeType) {
            case EAN_8:
                this.completetCodeLength = 8;
                break;
            case EAN_16:
                this.completetCodeLength = 16;
                break;
            default:
                throw new Exception("Error!");
        }

    }

    public String calculateCheckDigit(String codeWithoutCheckDigit) {
        int multiplicator = oddWeight;
        int sum = 0;
        int lenght = codeWithoutCheckDigit.length();
        if (lenght % 2 == 1) {
            multiplicator = evenWeight;
        }
        for (char c : codeWithoutCheckDigit.toCharArray()) {
            String num = String.format("%c", c);
            int i = Integer.parseInt(num);
            sum += i * multiplicator;
            if (multiplicator == 3) {
                multiplicator = 1;
            } else {
                multiplicator = 3;
            }
        }
        int checkDigit = 10 - (sum % 10);
        if (checkDigit == 10) {
            checkDigit = 0;
        }
        return checkDigit + "";
    }

    public String addCheckDigitToCode(String codeWithoutCheckDigit) {
        return codeWithoutCheckDigit + calculateCheckDigit(codeWithoutCheckDigit);
    }

    public boolean verifyCodeCheckDigitIsOk(String codeWithCheckDigit) {
        String codeWithoutCheckDigit = codeWithCheckDigit.substring(0, codeWithCheckDigit.length() - 1);
        int multiplicator = oddWeight;
        int sum = 0;
        int lenght = codeWithoutCheckDigit.length();
        if (lenght % 2 == 1) {
            multiplicator = evenWeight;
        }

        calculateCheckSum(codeWithoutCheckDigit, multiplicator);

        int checkDigit = 10 - (sum % 10);
        if (checkDigit == 10) {
            checkDigit = 0;
        }
        return (checkDigit == (Integer.parseInt(codeWithCheckDigit.charAt(codeWithCheckDigit.length() - 1) + "")));
    }

    public CodeType getCodeType() {
        return codeType;
    }

    private void calculateCheckSum(String codeWithoutCheckDigit, int multiplicator) {
        int sum = 0;
        for (char c : codeWithoutCheckDigit.toCharArray()) {
            String num = String.format("%c", c);
            int i = Integer.parseInt(num);
            sum += i * multiplicator;
            if (multiplicator == evenWeight) {
                multiplicator = oddWeight;
            } else {
                multiplicator = evenWeight;
            }
        }
    }
}
