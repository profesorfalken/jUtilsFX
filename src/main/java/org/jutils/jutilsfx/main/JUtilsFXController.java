/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jutils.jutilsfx.main;

import java.net.URL;
import java.util.ResourceBundle;

import org.jutils.jutilsfx.jhardware.HardwareType;
import org.jutils.jutilsfx.jhardware.JHardwareFXController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JUtilsFXController implements Initializable {

    @FXML
    private AnchorPane mainPanel;

    @FXML
    protected void handleJHardwareAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/jutils/jutilsfx/jhardware/JHardwareFX.fxml"));
        AnchorPane panel = loader.load();
        panel.prefWidthProperty().bind(mainPanel.widthProperty());
        panel.prefHeightProperty().bind(mainPanel.heightProperty());

        //Set hardwareinfo type to controller
        JHardwareFXController controller = loader.<JHardwareFXController>getController();
        controller.loadData(HardwareType.fromLabel(((Hyperlink) event.getSource()).getText()));
        
        if (mainPanel.getChildren().size() > 0) {
        	mainPanel.getChildren().remove(0);
        }

        mainPanel.getChildren().add(panel);
    }

    @FXML
    protected void handleJProcessesAction(ActionEvent event) throws Exception {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("/org/jutils/jutilsfx/jprocesses/JProcessesFX.fxml"));
        panel.prefWidthProperty().bind(mainPanel.widthProperty());
        panel.prefHeightProperty().bind(mainPanel.heightProperty());
        
        if (mainPanel.getChildren().size() > 0) {
        	mainPanel.getChildren().remove(0);
        }
        mainPanel.getChildren().add(panel);
    }

    @FXML
    protected void handleJServicesAction(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Still not implemented", ButtonType.OK);
        alert.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        //Insert initialization here
    }
}
