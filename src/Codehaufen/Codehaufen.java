package Codehaufen;



/**
 * Created by Darksirion on 03.09.15.
 */
public class Codehaufen {

    //Controll

    /*  @FXML
    TreeView selectionTreeView;

    public void selectedTreeView() {

        TreeView<String> tree;

        //create root
        TreeItem<String> root, bucky;
        root = new TreeItem<>();
        root.setExpanded(true);
        //create child
        TreeItem<String> itemChild = new TreeItem<>("Child");
        itemChild.setExpanded(false);
        //root is the parent of itemChild
        root.getChildren().add(itemChild);
        selectionTreeView.setRoot(root);


        test = makeBranch("Test", root);
        makeBranch("Test1", test);
        makeBranch("Test2", test);
        makeBranch("Test3", test);

        //create tree
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if (newValue != null)
                        System.out.println(newValue.getValue());
        });


    }


    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    */



    //StartUI


    /** small helper class for handling tree loading events. */
    /*
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //final Controller controller = root.<Controller>getController();

        // continuously refresh the TreeItems.
        // demonstrates using controller methods to manipulate the controlled UI.
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(3),
                        new TreeLoadingEventHandler(controller)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // close the app if the user clicks on anywhere on the window.
       // root.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      //      @Override public void handle(MouseEvent t) {
       //         primaryStage.hide();
       //     }
       // });

*/

      /* private class TreeLoadingEventHandler implements EventHandler<ActionEvent> {
        private Controller controller;
        private int idx = 0;

        TreeLoadingEventHandler(Controller controller) {
            this.controller = controller;
        }

        @Override public void handle(ActionEvent t) {
            controller.loadTreeItems("Loaded " + idx, "Loaded " + (idx + 1), "Loaded " + (idx + 2));
            idx += 3;
        }
    }
*/







}
