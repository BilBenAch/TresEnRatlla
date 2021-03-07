package Controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.robot.Robot;

import java.awt.event.InputEvent;
import java.net.URL;
import java.util.*;

import static javax.xml.xpath.XPathConstants.NODE;

public class TresEnRatllaControlador implements Initializable {

    public Button btn00;
    public Button btn01;
    public Button btn02;
    public Button btn10;
    public Button btn11;
    public Button btn12;
    public Button btn20;
    public Button btn21;
    public Button btn22;
    public RadioButton jugadorVSjugador = new RadioButton("Jugador VS Jugador");
    public RadioButton jugadorVSjCPU = new RadioButton("Jugador VS CPU");
    public RadioButton CPUVSCPU = new RadioButton("CPU VS CPU");
    public Button startButton = new Button();
    String jugador1Nom;
    String jugador2Nom;
    boolean jugadorVSjugadorBoolean = false;
    Button[] btns = new Button[9];
    int turno = 1;
    char[][] tablero = new char[3][3];
    Integer x, y;
    Button btn = new Button();
    int numero = 0;
    int tipoPartida = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializartablero();
        btns[0] = btn00;
        btns[1] = btn01;
        btns[2] = btn02;
        btns[3] = btn10;
        btns[4] = btn11;
        btns[5] = btn12;
        btns[6] = btn20;
        btns[7] = btn21;
        btns[8] = btn22;
        jugadorVSjugador.setText("Jugador vs Jugador");
        CPUVSCPU.setText("CPU VS CPU");
        jugadorVSjCPU.setText("Jugador vs CPU");
        seleccionarPartida();
    }


    public void CpuVSCpu() {

        turno = 1;

        while (!estaLLeno()) {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (turno == 1) {
                        char aux = (char) ((char) +'O' + (Math.random() * 4));
                        if (aux == 'O') {
                            tablero[i][j] = aux;
                            turno = 0;
                        }
                    } else if (turno == 0) {
                        char aux = (char) ((char) +'X' + (Math.random() * 4));
                        if (aux == 'X') {
                            tablero[i][j] = aux;
                            turno = 1;
                        }
                    }

                }
            }
        }
        //le asigno la tabla del terminal a los botones
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                arrayList.add(String.valueOf(tablero[i][j]));
            }
        }
        for (int i = 0; i < 9; i++) {
            btns[i].setText(String.valueOf(arrayList.get(i)));
        }

        System.out.println(arrayList.toString());

        mostrarTablero();
        comprobarGanador();
    }

    //Descomentar luego
//    public void playerVSCpu() {
//        MouseEvent mouseEvent = null;
//        if (turno == 1) {
//            onMouseClicked(null);
//        } else if (turno == 0) {
//            tiradaCpu();
//        }
//
//        mostrarTablero();
//        comprobarGanador();
//    }

//    public void onMouseClicked(MouseEvent mouseEvent) {
//
//        System.out.println("entro");
//        System.out.println("tipo partida " + tipoPartida);
//
//        if (tipoPartida == 0) {
//            seleccionarPartida();
//            seleccionar();
//
//        }
//
//        btn = (Button) mouseEvent.getSource();
//
//
//        x = Integer.parseInt(((Button) mouseEvent.getSource()).getId().substring(3, 4));
//        y = Integer.parseInt(((Button) mouseEvent.getSource()).getId().substring(4, 5));
//
////        System.out.println(x + y);
//
//        if (tipoPartida == 1) {
//            if (turno == 1) {
//                btn.setText("O");
//                tablero[x][y] = 'O';
//                turno = 0;
//                btn.setDisable(true);
//            } else if (turno == 0) {
//                btn.setText("X");
//                tablero[x][y] = 'X';
//                turno = 1;
//                btn.setDisable(true);
//            }
//        }
//
//        if (tipoPartida == 2) {
//
//            if (turno == 1) {
//                btn.setText("O");
//                tablero[x][y] = 'O';
//                turno = 0;
//                btn.setDisable(true);
//
//            }
//            //tiro bot
//            else if (turno == 0) {
//
//                boolean comprobarPosicion = true;
//                while (comprobarPosicion) {
//                    int posoicionRandomBotonCpu = (int) (Math.random() * 9);
//                    System.out.println(posoicionRandomBotonCpu);
//                    System.out.println(btns[posoicionRandomBotonCpu].getId());
//                    try {
//                        if (btns[posoicionRandomBotonCpu].getText().isEmpty()) {
//
//                        }
//                    } catch (NullPointerException e) {
//                        Integer posicionXconsola = Integer.parseInt(btns[posoicionRandomBotonCpu].getId().substring(3, 4));
//                        Integer posicionYconsola = Integer.parseInt(btns[posoicionRandomBotonCpu].getId().substring(4, 5));
//                        btns[posoicionRandomBotonCpu].setText("X");
//                        tablero[posicionXconsola][posicionYconsola] = 'X';
//                        btns[posoicionRandomBotonCpu].setDisable(true);
//                        turno = 1;
//                        comprobarPosicion = false;
//                    }
//                }
//            }
//        }
//
//        comprobarGanador();
//        mostrarTablero();
//
//    }


    //descomentar luego
//    public void tiradaCpu() {
//        //tiro bot
//        boolean comprobarPosicion = true;
//        while (comprobarPosicion) {
//            int posoicionRandomBotonCpu = (int) (Math.random() * 9);
//            System.out.println(posoicionRandomBotonCpu);
//            System.out.println(btns[posoicionRandomBotonCpu].getId());
//            try {
//                if (btns[posoicionRandomBotonCpu].getText().isEmpty()) {
//
//                }
//            } catch (NullPointerException e) {
//                Integer posicionXconsola = Integer.parseInt(btns[posoicionRandomBotonCpu].getId().substring(3, 4));
//                Integer posicionYconsola = Integer.parseInt(btns[posoicionRandomBotonCpu].getId().substring(4, 5));
//                btns[posoicionRandomBotonCpu].setText("X");
//                tablero[posicionXconsola][posicionYconsola] = 'X';
//                btns[posoicionRandomBotonCpu].setDisable(true);
//                turno = 1;
//                comprobarPosicion = false;
//            }
//        }
//
//
////        mostrarTablero();
////        comprobarGanador();
//    }


    void mostrarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    boolean estaLLeno() {
        boolean lleno = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    lleno = false;
                }
            }
        }
        return lleno;
    }


    public void comprobarGanador() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");

        if (estaLLeno() && !(comprobarGanadorFilas() || comprobarGanadorColumnas() || comprobarganadorDiagonal())) {
            alert.setTitle("Empate");
            alert.showAndWait();
            mostrarTablero();
            seleccionarPartida();
        }
        if (comprobarGanadorColumnas()) {
            alert.setTitle("Ganador Columnas");
            alert.showAndWait();
            mostrarTablero();
            seleccionarPartida();
            if (tipoPartida == 1) {
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> System.out.println("Your name: " + name));
            }
        }
        if (comprobarganadorDiagonal()) {
            alert.setTitle("Ganador Diagonal");
            alert.showAndWait();
            mostrarTablero();
            seleccionarPartida();
            if (tipoPartida == 1) {
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> System.out.println("Your name: " + name));
            }
        }
        if (comprobarGanadorFilas()) {
            alert.setTitle("Ganador filas");
            alert.showAndWait();
            mostrarTablero();
            seleccionarPartida();
            if (tipoPartida == 1) {
                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> System.out.println("Your name: " + name));
            }
        }
    }


    public boolean comprobarGanadorColumnas() {
        for (int j = 0; j < 3; j++) {
            if ((tablero[0][j] == 'X') && (tablero[1][j] == 'X') && (tablero[2][j] == 'X')) {
                return true;
            } else if (tablero[0][j] == 'O' && tablero[1][j] == 'O' && tablero[2][j] == 'O') {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarGanadorFilas() {
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == 'X') && (tablero[i][1] == 'X') && (tablero[i][2] == 'X')) {
                return true;
            } else if ((tablero[i][0] == 'O') && (tablero[i][1] == 'O') && (tablero[i][2] == 'O')) {
                return true;
            }
        }
        return false;
    }


    public boolean comprobarganadorDiagonal() {
        if (((tablero[0][0] == 'X') && (tablero[1][1] == 'X') && (tablero[2][2] == 'X'))
                || (tablero[0][2] == 'X') && (tablero[1][1] == 'X') && (tablero[2][0] == 'X')) {
            return true;
        } else if (((tablero[0][0] == 'O') && (tablero[1][1] == 'O') && (tablero[2][2] == 'O'))
                || (tablero[0][2] == 'O') && (tablero[1][1] == 'O') && (tablero[2][0] == 'O')) {
            return true;
        }
        return false;
    }


    void inicializartablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    //cambiar nombre
    void restartButtons() {

        for (int i = 0; i < btns.length; i++) {
            btns[i].setText("");
            btns[i].setDisable(false);
        }

    }

    //cambiar nombre
    void seleccionar() {
        for (int i = 0; i < btns.length; i++) {
            btns[i].setText("");
            btns[i].setDisable(true);
        }
    }


    public void onClickedStart(MouseEvent mouseEvent) {
        numero = 1;

    }

    public void onClick(MouseEvent mouseEvent) {

    }


    public void seleccionarPartida() {
        inicializartablero();
        seleccionar();
        startButton.setDisable(false);
        jugadorVSjugador.setDisable(false);
        jugadorVSjCPU.setDisable(false);
        CPUVSCPU.setDisable(false);
        ToggleGroup radioGroup = new ToggleGroup();

        jugadorVSjugador.setToggleGroup(radioGroup);
        jugadorVSjCPU.setToggleGroup(radioGroup);
        CPUVSCPU.setToggleGroup(radioGroup);

        jugadorVSjugador.setOnMouseClicked(mouseEvent -> tipoPartida = 1);

        jugadorVSjCPU.setOnMouseClicked(mouseEvent -> tipoPartida = 2);

        CPUVSCPU.setOnMouseClicked(mouseEvent -> tipoPartida = 3);

        startButton.setOnAction(actionEvent -> {
            if (tipoPartida == 0) {
                seleccionar();
            } else {
                jugadorVSjugador.setDisable(true);
                jugadorVSjCPU.setDisable(true);
                CPUVSCPU.setDisable(true);
                startButton.setDisable(true);
                restartButtons();
                if (tipoPartida == 3) {
                    seleccionar();
                    CpuVSCpu();
                    startButton.setDisable(true);
                }
//                if (tipoPartida == 2) {
//                    seleccionar();
//                    playerVSCpu();
//                }
            }
        });

    }
    public void onAction(ActionEvent actionEvent) {
        if (tipoPartida == 2) {
            jugadorVScpuu(actionEvent);
        }
    }

//    public void onMouseClicked(MouseEvent mouseEvent) {
//        if (tipoPartida == 2) {
//            turno =1;
//            jugadorVScpu(mouseEvent);
//        }
//    }


    public void jugadorVScpuu(ActionEvent event) {
        Button button = (Button) event.getSource();
        List<Button> buttons = new ArrayList<Button>(Arrays.asList(btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22));
        x = Integer.parseInt(((Button) event.getSource()).getId().substring(3, 4));
        y = Integer.parseInt(((Button) event.getSource()).getId().substring(4, 5));

        if (turno == 1) {
            button.setText("O");
            tablero[x][y] = 'O';
            turno = 0;
            button.setDisable(true);
        }
        //tiro bot
        else if (turno == 0) {
            System.out.println("entro");
            boolean comprobarPosicion = true;
            while (comprobarPosicion) {
                int posoicionRandomBotonCpu = (int) (Math.random() * 9);
                System.out.println(posoicionRandomBotonCpu);
                System.out.println(buttons.get(posoicionRandomBotonCpu).getId());
                if (buttons.get(posoicionRandomBotonCpu).getText().equals("")) {
                    Integer posicionXconsola = Integer.parseInt(buttons.get(posoicionRandomBotonCpu).getId().substring(3, 4));
                    Integer posicionYconsola = Integer.parseInt(buttons.get(posoicionRandomBotonCpu).getId().substring(4, 5));
                    buttons.get(posoicionRandomBotonCpu).setText("X");
                    tablero[posicionXconsola][posicionYconsola] = 'X';
                    buttons.get(posoicionRandomBotonCpu).setDisable(true);
                    turno = 1;
                    comprobarPosicion = false;

                }
            }
            mostrarTablero();
            comprobarGanador();
        }
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
    }
}



