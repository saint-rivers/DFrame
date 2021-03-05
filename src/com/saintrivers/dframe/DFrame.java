package com.saintrivers.dframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class DFrame extends JFrame{

    JPanel masterPanel = new JPanel();
    FormPanel formPanel;
    ButtonPanel buttonPanel;

    public DFrame(String name, String[] fieldNames, String[] buttonNames){
        masterPanel.setLayout(new GridLayout(2,1));
        masterPanel.setBorder(new EmptyBorder(10,10,10,10));
        formPanel = new FormPanel(fieldNames);
        buttonPanel = new ButtonPanel(buttonNames);
        masterPanel.add(formPanel);
        masterPanel.add(buttonPanel);

        initializeFrame(name);
    }

    void initializeFrame(String name){
        this.setTitle(name);
        this.add(masterPanel);
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class DFrameController {

    DFrame frameView;

    static String[] fieldNames = {"field1","field2","field3"};
    static String[] buttonNames = {"a","b"};

    DFrameController(DFrame frameView){
        this.frameView = frameView;
    }

    public static void main(String[] args) {
        DFrameController controller = new DFrameController(
                new DFrame("default", fieldNames, buttonNames)
        );
        controller.addListenersToButtons();
    }

    void addListenersToButtons(){
        Map<String, JButton> map = frameView.buttonPanel.buttonsMap;
        for (String name: buttonNames){
            map.get(name).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    buttonFunctionOf(name);
                }
            });
        }
    }

    void buttonFunctionOf(String buttonNames){
        Map<String, JButton> map = frameView.buttonPanel.buttonsMap;
        switch (buttonNames){
            case "a":
                System.out.println("take input");
                break;
            case "b":
                System.out.println("exit");
                break;
            default:
                break;
        }
    }

}

class FormPanel extends JPanel {

    public Map<String, JTextField> fieldNameDictionary = new HashMap<>();

    public FormPanel(String[] textFieldNames){

        this.setLayout(new GridLayout(textFieldNames.length, 2, 5, 5));
        loadLabelsAndFields(textFieldNames);
        this.setBorder(new EmptyBorder(10,10,10,10));
        System.out.println("Form panel loaded successfully");
    }

    void loadLabelsAndFields(String[] textFieldNames){
        for(int i = 0; i < textFieldNames.length; i++){

            fieldNameDictionary.put(textFieldNames[i], new JTextField()); // add name and text field to map
            this.add(new JLabel(textFieldNames[i])); // add name as label to form
            this.add(fieldNameDictionary.get(textFieldNames[i])); // add text field to form
        }
    }
}

class ButtonPanel extends JPanel{

    public Map<String, JButton> buttonsMap = new HashMap<>();

    public ButtonPanel(String[] buttonNames){

        this.setLayout(new GridLayout(1, buttonNames.length,20,20));
        this.setBorder(new EmptyBorder(30,10,30,10));
        loadAllButtons(buttonNames);
        System.out.println("Form buttons loaded successfully");
    }

    void loadAllButtons(String[] buttonNames){
        for(int i = 0; i < buttonNames.length; i++){
            JButton button = new JButton(buttonNames[i]);
            buttonsMap.put(buttonNames[i], button);
            this.add(buttonsMap.get(buttonNames[i]));
        }
    }
}