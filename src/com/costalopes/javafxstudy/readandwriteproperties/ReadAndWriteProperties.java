/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.costalopes.javafxstudy.readandwriteproperties;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author joao
 */
public class ReadAndWriteProperties {

    public static void main(String... args ) {
        
        //
        // read and write prop
        //
        SimpleIntegerProperty intProp = new SimpleIntegerProperty();
        
        intProp.set(10);
        System.out.println(intProp.get());
        
        SimpleStringProperty stringProp = new SimpleStringProperty("IntialValue");
        System.out.println(stringProp.get());
        
        //
        // read only props
        //
        ReadOnlyBooleanWrapper boolWrapper = new ReadOnlyBooleanWrapper(true);
        ReadOnlyBooleanProperty readOnlyBoolProp = boolWrapper.getReadOnlyProperty();
        
        System.out.println(readOnlyBoolProp.get());
        
        // se quero mudar o valor de uma propriedade read only, tenho que mudar o valor do Wrapper
        boolWrapper.set(false);
        System.out.println(readOnlyBoolProp.get());
        
    }
    
}
