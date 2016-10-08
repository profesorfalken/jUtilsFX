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
package org.jutils.jutilsfx.jprocesses;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JProcessesFXController implements Initializable {
    @FXML private TableView processesTable;
    @FXML private TableColumn pidColumn;
    @FXML private TableColumn processNameColumn;
    @FXML private TableColumn processTimeColumn;
    
    @FXML private Button btnKillProcess;
    
    private ObservableList<ObservableList<String>> tableItems;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        tableItems = FXCollections.observableArrayList();

        List<ProcessInfo> processesList = JProcesses.getProcessList();
        
        for(final ProcessInfo process : processesList) {
            tableItems.add(FXCollections.observableArrayList(process.getPid(), process.getTime(), process.getName()));
        }       
        
        processesTable.setItems(tableItems);

        pidColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().get(0));
            }
        });

        processNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().get(2));
            }
        });
        
        processTimeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().get(1));
            }
        });
    }    
    
    @FXML
    protected void handleBtnKillProcess(ActionEvent event) throws Exception {
        ObservableList<String> selectedRow = (ObservableList<String>)processesTable.getSelectionModel().getSelectedItems().get(0);
        JProcesses.killProcess(Integer.parseInt((String)selectedRow.get(0)));
        
        tableItems.remove(selectedRow);
    }
    
}
