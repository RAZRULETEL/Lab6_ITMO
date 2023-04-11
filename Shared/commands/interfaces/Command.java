package Shared.commands.interfaces;

import Shared.command_processing.ResultDTO;
import Shared.command_processing.StringDTO;
import Shared.resources.AbstractRouteCollection;

public interface Command {
    default ResultDTO validate(String[] args){
        if(args == null || args.length == 0)
            return new ResultDTO(true);
        return new StringDTO(false, "������� ����� ����������");
    };
    default ResultDTO isValid(){
        return new ResultDTO(true);
    };
    ResultDTO execute(AbstractRouteCollection collection);
    default StringDTO validateString(String arg, String whatValidate){
        String out = "";
        if(arg == null || arg.equals(""))
            out = "�� �� ������� "+whatValidate;
        if(out.equals(""))
            return new StringDTO(true, arg);
        return new StringDTO(false, out);
    }
    default StringDTO validateInt(String arg, String whatValidate){
        return validateInt(arg, whatValidate, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    default StringDTO validateInt(String arg, String whatValidate, int low_border, int high_border){
        if(low_border > high_border)
            throw new IllegalArgumentException("������ ������� ������ ���� ������ �������");
        Integer result = null;
        String out = "";
        if(arg == null || arg.equals(""))
            out = "������������ ���������, ������ �� �� ����� �������� "+whatValidate;
        else
            try{
                result = Integer.parseInt(arg);
            }catch (NumberFormatException ex){
                out = "������ �������� "+whatValidate+", ��������� �������� ���� int � �������� �� "+low_border+" �� "+high_border;
            }
        if(out.equals(""))
            return new StringDTO(true, result+"");
        return new StringDTO(false, out);
    }

}
