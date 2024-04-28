package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application {
	static Double_linked_list<District> newn = new Double_linked_list<>();
	static Node<Location> loc;
	static DNode<District> curr;
	//159 add District-->1204
	//193 Update the District --1256
	//230  delete District --1279
	//280 load
	//343next prev 
	//400--1072 number martyr of date(all district) 
	//insert location 485 -->973
	//delete location 516 --986
	//update location 548--1000
	//next location 641
	//insert martyr 663 1227
	//search martyr 744 855
	//delete martyr  778 823
	

	@Override
	public void start(Stage primaryStage) throws IOException, Exception {

		Button start = new Button("Choose any file");// to choose the file
		StackPane stack = new StackPane();
		stack.getChildren().addAll(start);
		Scene choose = new Scene(stack, 300, 400);
		primaryStage.setScene(choose);
		primaryStage.show();

		start.setOnAction(e -> {

			FileChooser filechoser = new FileChooser();
			
			
			
			File file = filechoser.showOpenDialog(primaryStage);
			if(file==null)
			{
				
				return;
			}
		
				if(!file.getName().equals("data (3).csv"))
				return;
			
			
			
			try {
				Scanner s1 = new Scanner(file);
				long lo = System.currentTimeMillis();
				s1.nextLine();// to read the data from the file
				while (s1.hasNext()) {

					String[] part = s1.nextLine().split(",");

					if (part.length != 6)// to cheak the data is valid
						continue;
					try {

						Integer.parseInt(part[2]);
					} catch (Exception x) {
						part[2] = "0";
					}

					Martyr m1 = new Martyr(part[0].trim(), part[1].trim(), Integer.parseInt(part[2].trim()),
							part[3].trim(), part[4].trim(), part[5].toLowerCase().trim().charAt(0));

					District d1 = new District();
					d1.setName(part[4]);

					if (newn.find(d1) == null) // to cheake the District is exist
					{
						newn.insert(d1);
					}

					Location location = new Location();
					location.setNameofLocation(part[3]);

					if (newn.find(d1).getData().getLocation().find(location) == null)// to cheake the Location is exist
					{
						newn.find(d1).getData().getLocation().insert(location);

					}

					newn.find(d1).getData().getLocation().find(location).getData().getMartyr().insert(m1);// insert the
																											// martyr
																											// from the
																											// file line
																											// by line

				}
			} catch (Exception x) {
				// TODO: handle exception
			}

			newn.traverce();
			District ll = new District();
			ll.setName("bethlehem");
			try {
				newn.find(ll).getData().getLocation().travrce();// to dosnt disply ane Exception
				curr = newn.getHead().getNext();
				loc = newn.getHead().getNext().getData().getLocation().getHead().getNext();
			} catch (Exception ee1) {
				// TODO: handle exception
			}

			Label l1 = new Label("Please choose");// make javafx
			Button b1 = new Button("District Screen");
			Button location = new Button("Location Screen:");
			ImageView imag = new ImageView("File:C:\\Users\\HP\\Pictures\\Screenshots\\nemer.jpg");
			VBox v1 = new VBox(imag);
			v1.getChildren().addAll(l1, b1, location);
			v1.setAlignment(Pos.CENTER);
			v1.setSpacing(20);
			Scene scene = new Scene(v1, 400, 450);
			primaryStage.setScene(scene);// to disply main scene
			primaryStage.show();
			b1.setOnAction(x -> {// make javafx
				Button add = new Button("insert new district");
				Button updat = new Button("         update a district             ");
				Button delet = new Button("         delete a district             ");
				Button totalOfDate = new Button("total number of martyrs fr a given dateo");
				Button load = new Button("load the first location in this district ");

				Button disply = new Button("disply the District by sorted");
				Label lm = new Label();

				Button back = new Button("Back");
				add.setPrefWidth(225);
				updat.setPrefWidth(225);
				delet.setPrefWidth(225);

				totalOfDate.setPrefWidth(225);
				load.setPrefWidth(225);
				disply.setOnAction(e5 -> {
					lm.setText(newn.toString());

				});
				VBox v2 = new VBox();

				Scene ss = new Scene(v2, 500, 650);
				primaryStage.setScene(ss);// secand scene
				ImageView image = new ImageView("File:C:\\Users\\HP\\Pictures\\Screenshots\\pos.jpg");
				image.setFitWidth(400);
				image.setFitHeight(200);

				add.setOnAction(e1 -> {// to add District
					Label label = new Label("insert data");// make javafx scene
					TextField t1 = new TextField();
					t1.setPrefWidth(250);
					Button bb = new Button("add");
					HBox h1 = new HBox();
					h1.getChildren().addAll(label, t1, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox vv = new VBox(image);
					Button back1 = new Button("Back");
					Label displyLabel = new Label();
					vv.getChildren().addAll(h1, displyLabel, back1);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene sadd = new Scene(vv, 400, 400);
					primaryStage.setScene(sadd);

					bb.setOnAction(e2 -> {

						if (add(t1.getText().trim()) == false)// your sure is inserting
						{
							disply.setText("pleade enter valeid data");

						}
						t1.setText("");
					});
					back1.setOnAction(e3 -> // to back to secand scene
					{
						primaryStage.setScene(ss);
					});

				});
				updat.setOnAction(e3 -> // Update the District (change the name)
				{
					Label label = new Label("updat data(change name)");// make javafx to update
					Label l3 = new Label("old name of District");
					TextField t1 = new TextField();
					t1.setPrefWidth(100);
					Label l2 = new Label("new name of District");
					TextField t2 = new TextField();
					t2.setPrefWidth(100);
					Button bb = new Button("updat");
					HBox h1 = new HBox();
					Label displyLabel = new Label();
					h1.getChildren().addAll(l3, t1, l2, t2, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox vv = new VBox(image);
					Button back1 = new Button("Back");
					vv.getChildren().addAll(label, h1, displyLabel, back1);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene s4 = new Scene(vv, 500, 400);
					primaryStage.setScene(s4);
					bb.setOnAction(e5 -> // to back to secande scene
					{
						if (update(t1.getText().trim(), t2.getText().trim()) == false) {
							displyLabel.setText("sorry dosent exist District have this name");

						}
					});

					back1.setOnAction(e4 -> {
						primaryStage.setScene(ss);
					});

				});

				delet.setOnAction(e6 -> // delete District
				{// make javafx to delete
					Label deletlabel = new Label("please enter District name");
					TextField dleettext = new TextField();
					Button delete = new Button("Delet");
					Label display = new Label();
					Button Back = new Button("Back");
					HBox h2 = new HBox();
					h2.setAlignment(Pos.CENTER);
					h2.setSpacing(20);
					h2.getChildren().addAll(deletlabel, dleettext, delete);
					VBox vv = new VBox();
					vv.getChildren().addAll(h2, display, Back);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene s3 = new Scene(vv, 400, 400);
					primaryStage.setScene(s3);
					Back.setOnAction(e5 -> // to back to secande scene
					{
						primaryStage.setScene(ss);

					});
					delete.setOnAction(e5 -> // delet District by delet methods
					{
						if (delet(dleettext.getText().trim()) == false) {
							display.setText("please enter valid value");
							dleettext.setText("");
						} else {
							display.setText("the delet is succsefuly");
						}

					});

				});

				Button next = new Button("go next district");// to Navigate throw districts
				Button prev = new Button("go previous district");
				HBox hh = new HBox();
				Label loc = new Label(curr.getData().getName());
				hh.getChildren().addAll(prev, next, loc);

				hh.setSpacing(20);

				Button Back = new Button("Back");

				v2.getChildren().addAll(hh, add, updat, delet, totalOfDate, load, disply, lm, Back);
				v2.setAlignment(Pos.CENTER);
				v2.setSpacing(20);
				Back.setOnAction(e3 -> {
					primaryStage.setScene(scene);
				});
				load.setOnAction(e1 -> {// to a load location in location secne
					Main.loc = curr.getData().getLocation().getHead().getNext();
				});
//to show data when Navigate throw districts
				hh.setAlignment(Pos.CENTER);
				Label n1 = new Label("Total number of martyrs");
				Label l2 = new Label();
				l2.setStyle("-fx-border-color: black;");
				HBox hh1 = new HBox();
				hh1.getChildren().addAll(n1, l2);
				hh1.setSpacing(20);
				hh1.setAlignment(Pos.CENTER);
				Label n2 = new Label("Total male and female martyrs");
				Label n3 = new Label("number of male:");
				Label n4 = new Label();
				Label n5 = new Label("number of female");
				Label n6 = new Label();
				HBox hh2 = new HBox();
				hh2.getChildren().addAll(n2, n3, n4, n5, n6);
				hh2.setSpacing(20);
				hh2.setAlignment(Pos.CENTER);

				Label n7 = new Label("Average martyrs ages");
				Label n8 = new Label();
				HBox hh3 = new HBox();
				hh3.getChildren().addAll(n7, n8);
				hh3.setSpacing(20);
				hh3.setAlignment(Pos.CENTER);
				Label n9 = new Label("date has the maximum number of martyrs");
				Label n10 = new Label();
				HBox hh4 = new HBox();
				l2.setText(totalAll_number() + "");// to disply total martyr in District

				n4.setText(male() + "");// all male in District
				n6.setText(female() + "");// all female in District

				n8.setText(avaryge() + "");//avaryge martyr in District
				n10.setText(max() + "");
				hh4.getChildren().addAll(n9, n10);
				hh4.setSpacing(20);
				hh4.setAlignment(Pos.CENTER);
				VBox vv = new VBox();
				Button Back1 = new Button("Back");
				v2.getChildren().addAll(hh1, hh2, hh3, hh4);

				n4.setStyle("-fx-border-color: black;");
				n6.setStyle("-fx-border-color: black;");
				n8.setStyle("-fx-border-color: black;");
				n10.setStyle("-fx-border-color: black;");
				l2.setPrefWidth(40);
				n4.setPrefWidth(30);
				n6.setPrefWidth(30);
				n8.setPrefWidth(40);
				n10.setPrefWidth(100);

				Back1.setOnAction(e5 -> {
					primaryStage.setScene(ss);

				});
				next.setOnAction(e7 -> {

					if (curr.getNext() == null) {
						return;
					}

					else {//to search and disply data
						loc.setText(curr.getNext().getData().getName());

						curr = curr.getNext();
						l2.setText(totalAll_number() + "");

						n4.setText(male() + "");
						n6.setText(female() + "");

						n8.setText(avaryge() + "");
						n10.setText(max() + "");

					}

				});
				prev.setOnAction(e7 -> {
//when you move between District
					if (curr.getPrev() == null) {

						return;
					}

					else {
						if(curr.getPrev().getData()!=null) {
						loc.setText(curr.getPrev().getData().getName());
						
						curr = curr.getPrev();
						l2.setText(totalAll_number() + "");

						n4.setText(male() + "");
						n6.setText(female() + "");
						if (max() == null)
							n10.setText("");
						else {
							n10.setText(max() + "");
						}

						n8.setText(avaryge() + "");
						}
					}

				});

				totalOfDate.setOnAction(e8 -> {///total number have this date 

					Label labeldate = new Label("please enter the date");
					TextField t = new TextField();
					Button date = new Button("Enter");
					HBox h1 = new HBox();
					h1.setSpacing(20);
					h1.getChildren().addAll(labeldate, t, date);
					h1.setAlignment(Pos.CENTER);
					Label l = new Label();
					Button Back2 = new Button("Back");
					VBox v = new VBox();
					v.getChildren().addAll(h1, l, back);
					v.setAlignment(Pos.CENTER);
					v.setSpacing(20);
					Scene s4 = new Scene(v, 400, 400);
					primaryStage.setScene(s4);
					back.setOnAction(e5 -> {
						primaryStage.setScene(ss);
					});
					date.setOnAction(e5 -> {//to sure your enter valid value 
						if (t.getText().equals("") || t.getText().split("/").length != 3) {
							l.setText("please enter a true value");
							return;
						}
						try {
							Integer.parseInt(t.getText().split("/")[0]);
							Integer.parseInt(t.getText().split("/")[1]);
							Integer.parseInt(t.getText().split("/")[2]);
						} catch (Exception e1) {
							l.setText("please enter a true value");
							return;
						}
						l.setText(total_Number(t.getText().trim()) + "");

					});

				});

			});

			location.setOnAction(e10 -> {//secne location 

				if(loc==null)
				{
					return ;
				}
				
				Button next = new Button("Next Loc");//to move between location
				Label loc1 = new Label(loc.getData().getNameofLocation());
				HBox hh = new HBox();
				hh.getChildren().addAll(next, loc1);
				hh.setAlignment(Pos.CENTER);
				hh.setSpacing(20);//to make the scene
				Button add = new Button("insert new Location");
				Button updat = new Button("         update a Location             ");
				Button delet = new Button("         delete a Location             ");

				Button disply = new Button("disply the Location by sorted");

				Button insert = new Button("insert a new Martyr");
				Button ubdate = new Button("ubdate or delet a new martyr");
				Button search = new Button("search for martyr by oart of name");
				Label display = new Label();
				VBox vv1 = new VBox();
				Button back = new Button("Back");
				vv1.getChildren().addAll(hh, add, updat, delet, disply, insert, ubdate, search, back, display);
				vv1.setAlignment(Pos.CENTER);
				vv1.setSpacing(20);
				Scene scane1 = new Scene(vv1, 600, 700);
				primaryStage.setScene(scane1);
				back.setOnAction(e1 -> {

					primaryStage.setScene(scene);
				});

				disply.setOnAction(e1 -> {//displye travirce location
					display.setText(curr.getData().getLocation().travirce());

					System.out.println(curr.getData().getLocation().travirce());
				});

				Label lm = new Label();
				add.setPrefWidth(225);
				updat.setPrefWidth(225);
				delet.setPrefWidth(225);
				insert.setPrefWidth(225);
				ubdate.setPrefWidth(225);

				search.setPrefWidth(225);

				disply.setPrefWidth(225);
				add.setOnAction(e1 -> {//insert a new location
					Label label = new Label("insert data");
					TextField t1 = new TextField();
					t1.setPrefWidth(250);
					Button bb = new Button("add");
					HBox h1 = new HBox();
					h1.getChildren().addAll(label, t1, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox vv = new VBox();
					Button back1 = new Button("Back");
					Label displyLabel = new Label();
					vv.getChildren().addAll(h1, displyLabel, back1);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene sadd = new Scene(vv, 400, 400);
					primaryStage.setScene(sadd);
					back1.setOnAction(e3 -> {
						primaryStage.setScene(scane1);
					});
					bb.setOnAction(e2 -> {

						if (insert(t1.getText().trim()) == false) {
							displyLabel.setText("pleade enter valeid data");

						}
						t1.setText("");
					});

				});
				delet.setOnAction(e6 -> {//delete location
					Label deletlabel = new Label("please enter District name");
					TextField dleettext = new TextField();
					Button delete = new Button("Delet");
					Label display1 = new Label();
					Button Back = new Button("Back");
					HBox h2 = new HBox();
					h2.setAlignment(Pos.CENTER);
					h2.setSpacing(20);
					h2.getChildren().addAll(deletlabel, dleettext, delete);
					VBox vv = new VBox();
					vv.getChildren().addAll(h2, display1, Back);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene s3 = new Scene(vv, 400, 400);
					primaryStage.setScene(s3);
					Back.setOnAction(e5 -> {
						primaryStage.setScene(scane1);

					});
					delete.setOnAction(e5 -> {
						if (deletloc(dleettext.getText().trim()) == false) {
							display1.setText("please enter valid value");
							dleettext.setText("");
						} else {
							display1.setText("the delet is succsefuly");
						}

					});

				});
				updat.setOnAction(e3 -> {//ubdat location
					Label label = new Label("updat data(change name)");
					Label l3 = new Label("old name of District");
					TextField t1 = new TextField();
					t1.setPrefWidth(100);
					Label l2 = new Label("new name of District");
					TextField t2 = new TextField();
					t2.setPrefWidth(100);
					Button bb = new Button("updat");
					HBox h1 = new HBox();
					Label displyLabel = new Label();
					h1.getChildren().addAll(l3, t1, l2, t2, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox vv = new VBox();
					Button back1 = new Button("Back");
					vv.getChildren().addAll(label, h1, displyLabel, back1);
					vv.setAlignment(Pos.CENTER);
					vv.setSpacing(20);
					Scene s4 = new Scene(vv, 500, 400);
					primaryStage.setScene(s4);
					bb.setOnAction(e5 -> {
						if (updatelocation(t1.getText().trim(), t2.getText().trim()) == false) {
							displyLabel.setText("sorry dosent exist location have this name");

						} else {
							displyLabel.setText("the update is succssifly");
						}
					});

					back1.setOnAction(e4 -> {
						primaryStage.setScene(scane1);
					});

				});

				Label n1 = new Label("Total number of martyrs");
				Label l2 = new Label(total_number_loc() + "");
				l2.setStyle("-fx-border-color: black;");
				HBox hh1 = new HBox();
				hh1.getChildren().addAll(n1, l2);
				hh1.setSpacing(20);
				hh1.setAlignment(Pos.CENTER);
				Label n2 = new Label("Total male and female martyrs");
				Label n3 = new Label("number of male:");
				Label n4 = new Label();
				Label n5 = new Label("number of female");
				Label n6 = new Label();
				HBox hh2 = new HBox();
				hh2.getChildren().addAll(n2, n3, n4, n5, n6);
				hh2.setSpacing(20);
				hh2.setAlignment(Pos.CENTER);

				Label n7 = new Label("Average martyrs ages");
				Label n8 = new Label();
				HBox hh3 = new HBox();
				hh3.getChildren().addAll(n7, n8);
				hh3.setSpacing(20);
				hh3.setAlignment(Pos.CENTER);
				Label n9 = new Label("youngest");
				Label n10 = new Label();
				Label n11 = new Label("oldest");
				Label n12 = new Label();
				HBox hh4 = new HBox();

				hh4.getChildren().addAll(n9, n10);
				hh4.setSpacing(20);
				hh4.setAlignment(Pos.CENTER);
				HBox hh5 = new HBox();
				hh5.getChildren().addAll(n11, n12);
				hh5.setSpacing(20);
				hh5.setAlignment(Pos.CENTER);
				VBox vv = new VBox();
				l2.setText(total_number_loc() + "");

				n4.setText(male_location() + "");
				n6.setText(famale_location() + "");

				n8.setText(avaryge_loctation() + "");
				n10.setText(oldest_location() + "");
				n12.setText(youngest_location() + "");
				vv1.getChildren().addAll(hh1, hh2, hh3, hh4, hh5);

				n4.setStyle("-fx-border-color: black;");
				n6.setStyle("-fx-border-color: black;");
				n8.setStyle("-fx-border-color: black;");

				l2.setPrefWidth(40);
				n4.setPrefWidth(30);
				n6.setPrefWidth(30);
				n8.setPrefWidth(40);

				next.setOnAction(e7 -> {
					if (loc.getNext() == null) {
						loc = curr.getData().getLocation().getHead().getNext();

					} else {
						loc = loc.getNext();
					}

					
					loc1.setText(loc.getData().getNameofLocation());

					l2.setText(total_number_loc() + "");

					n4.setText(male_location() + "");
					n6.setText(famale_location() + "");

					n8.setText(avaryge_loctation() + "");
					n10.setText(oldest_location() + "");
					n12.setText(youngest_location() + "");

				});
				insert.setOnAction(e2 -> {
					Label ll1=new Label("inser name");
					TextField name=new TextField();
					Label ll2=new Label("insert date of martyr");
					TextField date=new TextField();
					Label ll3=new Label("insert age");
					TextField age=new TextField();
					Label ll4=new Label("insert location");
					TextField loca=new TextField();
					Label ll5=new Label("insert District");
					TextField  Dist=new TextField();
					Label ll6=new Label("insert gender");
					TextField gender=new TextField();
					GridPane porder=new GridPane();
					porder.add(ll1, 0, 0);
					porder.add(name, 1, 0);
					porder.add(ll2, 0, 1);
					porder.add(date, 1, 1);
					porder.add(ll3, 0, 2);
					porder.add(age, 1, 2);
					porder.add(ll4, 0, 3);
					porder.add(loca, 1, 3);
					porder.add(ll5, 0, 4);
					porder.add(Dist, 1, 4);
					porder.add(ll6, 0, 5);
					porder.add(gender, 1, 5);
					porder.setAlignment(Pos.CENTER);
					porder.setHgap(20);
					porder.setVgap(20);
					
					
							
					
					
					
					
					
					Label label = new Label("insert data");
				//	String t1 = new String();
				//	t1.setPrefWidth(250);
					Button bb = new Button("add");
					HBox h1 = new HBox();
					
					
					
					
					//h1.getChildren().addAll(label, t1, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox v = new VBox();
					Button back1 = new Button("Back");
					Label displyLabel = new Label();
					v.getChildren().addAll(porder, bb,displyLabel, back1);
					v.setAlignment(Pos.CENTER);
					v.setSpacing(20);
					Scene sadd = new Scene(v, 400, 400);
					primaryStage.setScene(sadd);
					back1.setOnAction(e3 -> {
						primaryStage.setScene(scane1);
					});
					bb.setOnAction(e1 -> {
						if(name.getText()==""||date.getText()==""||age.getText()==""||loca.getText()==""||Dist.getText()==""||gender.getText()=="")
						{
							displyLabel.setText("please enter valid value");
							return;
							
						}
						String h;
						h=name.getText().translateEscapes()+","+date.getText().trim()+","+age.getText().trim()+","+loca.getText().trim()+","+Dist.getText().trim()+","+gender.getText().trim();
						if (insert_martyr(h) == false) {
							displyLabel.setText("please enter valid value");

						} else {
							displyLabel.setText("the add is succsefly");
						}

					});

				});

				search.setOnAction(e1 -> {
					Label label = new Label("insert part of name");
					TextField t1 = new TextField();
					t1.setPrefWidth(250);
					Button bb = new Button("search");
					HBox h1 = new HBox();
					h1.getChildren().addAll(label, t1, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox v = new VBox();
					Button back1 = new Button("Back");
					Label displyLabel = new Label();
					v.getChildren().addAll(h1, displyLabel, back1);
					v.setAlignment(Pos.CENTER);
					v.setSpacing(20);
					Scene sadd = new Scene(v, 400, 400);
					primaryStage.setScene(sadd);
					back1.setOnAction(e3 -> {
						primaryStage.setScene(scane1);
					});
					bb.setOnAction(e2 -> {

						Martyr n = search(t1.getText().trim());
						if (n == null) {
							displyLabel.setText("Dosnt have any person have this name");
							return;

						}
						displyLabel.setText(n.toString());

					});

				});
				ubdate.setOnAction(e1 -> {
					Label label = new Label("insert name");
					TextField t1 = new TextField();
					t1.setPrefWidth(250);
					Button bb = new Button("delet");
					HBox h1 = new HBox();
					h1.getChildren().addAll(label, t1, bb);
					h1.setAlignment(Pos.CENTER);
					h1.setSpacing(20);
					VBox v = new VBox();
					Button back1 = new Button("Back");
					Label displyLabel = new Label();
					v.getChildren().addAll(h1, displyLabel, back1);
					v.setAlignment(Pos.CENTER);
					v.setSpacing(20);
					Scene sadd = new Scene(v, 400, 400);
					primaryStage.setScene(sadd);
					back1.setOnAction(e3 -> {
						primaryStage.setScene(scane1);
					});

					bb.setOnAction(e2 -> {
						if (delet_martyr(t1.getText().trim()) != null) {

							displyLabel.setText("Done");
						} else {
							displyLabel.setText("Dosnt have any person have this name");

						}

					});

				});
			});

		});

	}

	public static void main(String[] args) {

		launch(args);

	}

	public static Martyr delet_martyr(String name) {//delete any martyr 
		if (name == "")
			return null;

		DNode<District> dd = newn.getHead().getNext();
		Node<Location> currloc = newn.getHead().getNext().getData().getLocation().getHead().getNext();
		Node<Martyr> curr = currloc.getData().getMartyr().getHead().getNext();
		while (dd != null) {
			currloc = dd.getData().getLocation().getHead().getNext();
			for (; currloc != null; currloc = currloc.getNext()) {
				curr = currloc.getData().getMartyr().getHead().getNext();

				for (; curr != null; curr = curr.getNext()) {

					if (curr.getData().getName().contains(name) == true) {

						Node<Martyr> m1 = currloc.getData().getMartyr().find(curr.getData());
						dd.getData().getLocation().find(currloc.getData()).getData().getMartyr().delet(curr.getData());

						return m1.getData();

					}

				}

			}
			dd = dd.getNext();
		}

		return null;

	}

	public static Martyr search(String n) {//search of martyr by bart of name
		if (n == "")
			return null;

		DNode<District> dd = newn.getHead().getNext();
		try {
			dd.getData().getLocation().getHead().getNext().getData();
		
		}catch (Exception e) {
			dd=newn.getHead().getNext().getNext();
		}
		
		
		Node<Location> currloc = dd.getData().getLocation().getHead().getNext();
		Node<Martyr> curr = currloc.getData().getMartyr().getHead().getNext();
		while (dd != null) {
			currloc = dd.getData().getLocation().getHead().getNext();
			for (; currloc != null; currloc = currloc.getNext()) {
				curr = currloc.getData().getMartyr().getHead().getNext();

				for (; curr != null; curr = curr.getNext()) {

					if (curr.getData().getName().contains(n) == true) {
						return curr.getData();

					}

				}
			}
			dd = dd.getNext();
		}

		return null;

	}

	public static int male_location() {//Total number of male
		int sum = 0;

		Node<Martyr> curr = loc.getData().getMartyr().getHead().getNext();

		for (; curr != null; curr = curr.getNext()) {

			if (curr.getData().getGender() == 'm') {
				System.out.println(curr.getData().toString());
				sum += 1;

			}

		}

		return sum;

	}
	public static String youngest_location() {//youngest martyr in location
		int youngest = 120;

		String locmax = "";

		Node<Martyr> curr1 = loc.getData().getMartyr().getHead().getNext();

		for (; curr1 != null; curr1 = curr1.getNext()) {

			if (curr1.getData().getAge() < youngest) {
				youngest = curr1.getData().getAge();
				locmax = curr1.toString();
			}

		}

		return locmax;

	}

	public static String oldest_location() {//oldest martyr in location
		int oldest = 0;

		String locmax = "";

		Node<Martyr> curr1 = loc.getData().getMartyr().getHead().getNext();

		for (; curr1 != null; curr1 = curr1.getNext()) {

			if (curr1.getData().getAge() > oldest) {
				oldest = curr1.getData().getAge();
				locmax = curr1.toString();
			}

		}

		return locmax;

	}

	public static int famale_location() {//Total number of female
		int sum = 0;

		Node<Martyr> curr = loc.getData().getMartyr().getHead().getNext();

		for (; curr != null; curr = curr.getNext()) {

			if (curr.getData().getGender() == 'f') {

				sum += 1;

			}

		}

		return sum;

	}

	public static double avaryge_loctation() {// Average martyrs ages.

		double sum = 0;
		double counter = 0;

		Node<Martyr> curr = loc.getData().getMartyr().getHead().getNext();

		for (; curr != null; curr = curr.getNext()) {
			sum += curr.getData().getAge();

			counter++;

		}

		return sum / counter;

	}

	public static boolean insert(String n) {//insert a new location

		if (n.equals(""))
			return false;

		Location l1 = new Location();
		l1.setNameofLocation(n.trim());
		curr.getData().getLocation().insert(l1);
		return true;

	}

	public boolean deletloc(String name) {//delete location by name
		Location dd = new Location();
		dd.setNameofLocation(name);
		if (name.equals("") || curr.getData().getLocation().find(dd) == null) {

			return false;
		}

		curr.getData().getLocation().delet(dd);
		return true;

	}

	public static boolean updatelocation(String old, String newm) {//change name of location 

		Location d = new Location();
		d.setNameofLocation(old);

		if (curr.getData().getLocation().find(d) == null) {
			return false;

		}

		Location d1 = d;
		d1.setMartyr(curr.getData().getLocation().find(d).getData().getMartyr());
		curr.getData().getLocation().delet(curr.getData().getLocation().find(d).getData());
		d1.setNameofLocation(newm);

		curr.getData().getLocation().insert(d1);

		newn.traverce();

		return true;

	}

	public static int total_number_loc() {//total number of martyr in location
		int sum = 0;
		Node<Martyr> curr = loc.getData().getMartyr().getHead().getNext();
		while (curr != null) {

			sum += 1;

			curr = curr.getNext();

		}

		return sum;

	}

	public static String max() {//total number of martyr has this date
		int max = 0;
		int counter = 0;
		String loc = "";
		Node<Location> currloc = curr.getData().getLocation().getHead().getNext();
		String locmax = "";
		if (currloc == null)
			return null;
		Node<Martyr> martyr = currloc.getData().getMartyr().getHead().getNext();
		for (; currloc != null; currloc = currloc.getNext()) {
			martyr = currloc.getData().getMartyr().getHead().getNext();

			for (; martyr != null; martyr = martyr.getNext()) {
				counter = 0;
				Node<Martyr> curr1 = currloc.getData().getMartyr().getHead().getNext();

				loc = martyr.getData().getDate();
				for (; curr1 != null; curr1 = curr1.getNext()) {

					if (loc.equals(curr1.getData().getDate())) {
						counter++;
					}

				}
				if (max < counter) {
					max = counter;
					locmax = loc;
				}

			}

		}

		return locmax;

	}

	public static int total_Number(String n) {// total number make this date (n)
		String part[] = n.trim().split("/");
		int sum = 0;
		District dd = newn.getHead().getNext().getData();
		try {
			dd.getLocation().getHead().getNext().getData();
		
		}catch (Exception e) {
			dd=newn.getHead().getNext().getNext().getData();
		}
			
		
		Node<Location> currloc = dd.getLocation().getHead().getNext();
		Node<Martyr> martyr = currloc.getData().getMartyr().getHead().getNext();
		while (dd != null) {
			currloc = dd.getLocation().getHead().getNext();
			for (; currloc != null; currloc = currloc.getNext()) {
				martyr = currloc.getData().getMartyr().getHead().getNext();

				for (; martyr != null; martyr = martyr.getNext()) {
					String[] m = martyr.getData().getDate().split("/");

					if (Integer.parseInt(m[0]) == Integer.parseInt(part[0])
							&& Integer.parseInt(m[1]) == Integer.parseInt(part[1])
							&& Integer.parseInt(m[2]) == Integer.parseInt(part[2])) {
						sum += 1;

					}

				}

			}

			if (newn.find(dd).getNext() == null)
				break;

			dd = newn.find(dd).getNext().getData();

		}

		return sum;

	}

	public static double avaryge() { // Average age for all number in District

		double sum = 0;
		double counter = 0;
		Node<Location> currloc = curr.getData().getLocation().getHead().getNext();
		if (currloc == null)
			return 0;
		Node<Martyr> curr1 = currloc.getData().getMartyr().getHead().getNext();
		while (currloc != null) {
			curr1 = currloc.getData().getMartyr().getHead().getNext();

			for (; curr1 != null; curr1 = curr1.getNext()) {

				sum += curr1.getData().getAge();

				counter++;

			}

			currloc = currloc.getNext();

		}

		return sum / counter;

	}

	public static int male() {// all male in District
		int sum = 0;

		Node<Location> currloc = curr.getData().getLocation().getHead().getNext();

		if (currloc == null)
			return 0;

		Node<Martyr> curr = currloc.getData().getMartyr().getHead().getNext();
		while (currloc != null) {
			curr = currloc.getData().getMartyr().getHead().getNext();

			for (; curr != null; curr = curr.getNext()) {

				if (curr.getData().getGender() == 'm') {
					sum += 1;

				}

			}

			currloc = currloc.getNext();

		}

		return sum;

	}

	public static int female() {// all female in District
		int sum = 0;

		Node<Location> currloc = curr.getData().getLocation().getHead().getNext();
		if (currloc == null)
			return 0;
		Node<Martyr> curr = currloc.getData().getMartyr().getHead().getNext();
		while (currloc != null) {
			curr = currloc.getData().getMartyr().getHead().getNext();

			for (; curr != null; curr = curr.getNext()) {

				if (curr.getData().getGender() == 'f') {
					sum += 1;

				}

			}

			currloc = currloc.getNext();

		}

		return sum;

	}

	public static int totalAll_number() {// total number of martyr in District
		int sum = 0;
		Node<Location> curr1 = curr.getData().getLocation().getHead().getNext();
		if (curr1 == null)
			return 0;
		while (curr1 != null) {

			sum += curr1.getData().getMartyr().length();

			curr1 = curr1.getNext();

		}

		return sum;

	}

	public static boolean add(String n)// to insert anew District
	{

		if (n.equals(""))
			return false;
        
		District dd = new District();
		dd.setName(n);
		newn.insert(dd);

		return true;

	}

	public static boolean insert_martyr(String n) {//insert a new martyr by all data
		String[] part = n.split(",");
		if (part.length != 6) {
			System.out.println("please enter data valied");

			return false;
		}
		try {

			Integer.parseInt(part[2]);

		} catch (Exception e) {
			part[2] = "0";

		}
		District d1 = new District();
		d1.setName(part[4]);
		if (newn.find(d1) == null) {
			newn.insert(d1);
		}
		Location l1 = new Location();
		l1.setNameofLocation(part[3]);
		if (newn.find(d1).getData().getLocation().find(l1) == null) {
			System.out.println("no location");
			newn.find(d1).getData().getLocation().insert(l1);

		}
		Martyr m1 = new Martyr(part[0].trim(), part[1].trim(), Integer.parseInt(part[2].trim()), part[3].trim(),
				part[4].trim(), part[5].toLowerCase().trim().charAt(0));

		newn.find(d1).getData().getLocation().find(l1).getData().getMartyr().insert(m1);
		System.out.println(newn.find(d1).getData().getLocation().travirce());

		return true;

	}

	public static boolean update(String old, String newm) {//to change the name of District

		District d = new District();
		d.setName(old);

		if (newn.find(d) == null) {
			return false;

		}

		District d1 = new District();
		d1.setLocation(newn.find(d).getData().getLocation());
		newn.delet(d);
		d1.setName(newm);

		newn.insert(d1);
		newn.traverce();

		return true;

	}

	public boolean delet(String name)// delete District
	{
		District dd = new District();
		dd.setName(name);
		if (name.equals("") || newn.find(dd) == null) {

			return false;
		}

		newn.delet(dd);
		return true;

	}

}
