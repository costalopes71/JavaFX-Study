/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.costalopes.javafxstudy.bindings;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author joao
 */
public class BindingTask {

    
    public static void main(String... arg) {
        
        StringProperty lastNameProp = new SimpleStringProperty();
        StringProperty surNameProp = new SimpleStringProperty();
        
        //
        // Bind Unidirecional 
        //
        surNameProp.bind(lastNameProp);
        
        lastNameProp.set("Costa Lopes");
        
        System.out.println(surNameProp.get());
        
        //
        // se eu tento mudar o valor de uma propriedade alvo de um bind unidirecional, uma exception eh lancada
        //
//        surNameProp.set("Joao");
        
        //
        // Bind Bidirecional
        //
        
        StringProperty nome1 = new SimpleStringProperty();
        StringProperty nome2 = new SimpleStringProperty();
        
        nome1.bindBidirectional(nome2);
        
        nome1.set("Joao");
        nome2.set("Maria");
        
        // ambas as propriedades serao alteradas!
        System.out.println(nome1);
        System.out.println(nome2);
        
        nome1.set("Joao");
        nome2.set("Lopes");
        
        StringProperty primeiroNome = new SimpleStringProperty();
        StringProperty ultimoNome = new SimpleStringProperty();
        primeiroNome.set("Joao");
        ultimoNome.set("Lopes");
        
        StringProperty fullName = new SimpleStringProperty();
        fullName.bind(Bindings.concat(primeiroNome, " ", ultimoNome));
        
        System.out.println(fullName.get());
        
        //
        // binding usando Fluent API (qdo um valor nao eh exatamente a copia de um outro valor por exemplo)
        //
        
        IntegerProperty length = new SimpleIntegerProperty(18);
        IntegerProperty width = new SimpleIntegerProperty(13);
        
        IntegerProperty area = new SimpleIntegerProperty();
        area.bind(length.multiply(width));
        
        NumberBinding perimeter = length.add(width).multiply(2);
        
        System.out.println(area.get());
        System.out.println(perimeter.getValue());
        
        
        //
        // Low level binding
        //
        DoubleProperty radius = new SimpleDoubleProperty(1.5);
        
        DoubleBinding volume = new DoubleBinding() {

            {
                super.bind(radius);
            }

            @Override
            protected double computeValue() {
                return 4 / 3 * Math.PI*Math.pow(radius.get(), 3);
            }
        };
        
        System.out.println(volume.get());
        radius.set(2.5);
        System.out.println(volume.get());
        
    }
    
    
}
