package tiendamass;

import tiendamass.modelo.*;
import tiendamass.vista.VentanaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaLogin().setVisible(true);
        });
    }
}
