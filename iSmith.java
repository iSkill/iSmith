import java.awt.*;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.scene.SceneObject;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Camera;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import org.parabot.core.ui.components.LogArea;
import org.rev317.api.wrappers.hud.Item;

@ScriptManifest(author = "iSkill", category = Category.SMITHING, description = "Smelts any bar at Varrock (More locations soon!)", name = "iSmith", servers = { "PKHonor" }, version = 2.0)

public class iSmith extends Script implements Paintable, MessageListener{
    
      private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
     
      private static String Status;
      private static String BARTYPE;
      private  static int INGREDIENT;
      private static int  INGREDIENT2;
      private  static int PRODUCT;     
      private static int AMMO_MOULD;
      int PKP = 0;
      int BARS = 0;
      
      int XPGAINED = 0;
    private final Color color1 = new Color(0, 255, 255);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(3);

    private final Font font1 = new Font("Verdana", 1, 17);
    private final Font font2 = new Font("Verdana", 1, 11);

    static long StartTime;    
    static long startTime;
     public static String runTime(long i) {
        DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
        }
    
      public String getTimeRan() {
                final long currentTime = System.currentTimeMillis() - StartTime;
                String format = String.format(
                                "%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(currentTime),
                                TimeUnit.MILLISECONDS.toMinutes(currentTime)
                                                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                                                .toHours(currentTime)),
                                TimeUnit.MILLISECONDS.toSeconds(currentTime)
                                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                                                                .toMinutes(currentTime)));
                return format;
        }
    
      //GUI
     public static iSmithGUI g = new iSmithGUI();
      public static boolean guiWait = true;
     public static int INSTANCE;
     
      
      @Override
    public boolean onExecute() {
      g.setVisible(true);
      while(guiWait)
          sleep(500);
       if (guiWait == false)
       {
       
      startTime = System.currentTimeMillis();
      
      if (INSTANCE == 1){
          strategies.add(new SmithOneIngredient());
          strategies.add(new TeleportBankOneIngredient());
          strategies.add(new TeleportFurnaceOneIngredient());
          strategies.add(new WithdrawMethodOneIngredient()); 
          strategies.add(new DepositMethod());
          provide(strategies);
      }
      if (INSTANCE == 2) {
      strategies.add(new SmithRune());
      strategies.add(new TeleportBankRune());
      strategies.add(new TeleportFurnaceRune());
      strategies.add(new DepositMethod());
      strategies.add(new WithdrawMethodRune()); 
      provide(strategies);
      }
     if (INSTANCE == 3) {
      strategies.add(new SmithRune());
      strategies.add(new TeleportBankRune());
      strategies.add(new TeleportFurnaceRune());
      strategies.add(new DepositMethod());
      strategies.add(new WithdrawMethodRune()); 
      provide(strategies);
      }
     if (INSTANCE == 4) {
      strategies.add(new SmithRune());
      strategies.add(new TeleportBankRune());
      strategies.add(new TeleportFurnaceRune());
      strategies.add(new DepositMethod());
      strategies.add(new WithdrawMethodRune()); 
      provide(strategies);
      }
     if (INSTANCE == 5) {
      strategies.add(new SmithRune());
      strategies.add(new TeleportBankRune());
      strategies.add(new TeleportFurnaceRune());
      strategies.add(new DepositMethod());
      strategies.add(new WithdrawMethodRune()); 
      provide(strategies);
      }
     if (INSTANCE == 6) {
      strategies.add(new SmithRune());
      strategies.add(new TeleportBankRune());
      strategies.add(new TeleportFurnaceRune());
      strategies.add(new DepositMethod());
      strategies.add(new WithdrawMethodRune()); 
      provide(strategies);
      }
     
       
       
       
       } 
     
       return true;
    }

    
   

    public class SmithOneIngredient implements  Strategy  {
        SceneObject FURNACE;
        @Override
        public boolean activate() {
           for (SceneObject s : SceneObjects.getNearest(11666)){
               if (s != null
                       && Inventory.getCount(INGREDIENT) >0
                       && Players.getLocal().getAnimation() ==-1
                       )
               {
                   FURNACE = s;
                   return true;
               }
           }
           return false;
        }
        
        
        @Override
        public void execute() {
           
           if (FURNACE.isOnScreen()){
               FURNACE.interact("Smelt");
               Status = "Opening smith menu";
               Time.sleep(2800);
               Mouse.getInstance().click(289,407, false);
               Status = "Selecting bar to smith";
              Time.sleep(800);
              Mouse.getInstance().click(290, 482, true);
              Time.sleep(2000);
              Keyboard.getInstance().sendKeys("99");
              Status = "Smithing bars";
              Time.sleep(30000);
              
           } else if (!FURNACE.isOnScreen()) {
               Camera.turnTo(FURNACE);
               
           }
         }
        }
      public class SmithRune implements  Strategy  {
        SceneObject FURNACE;
        @Override
        public boolean activate() {
           for (SceneObject s : SceneObjects.getNearest(11666)){
               if (s != null
                       && Inventory.getCount(INGREDIENT) >0
                       && Inventory.getCount(INGREDIENT2) >0
                       && Players.getLocal().getAnimation() ==-1
                       )
               {
                   FURNACE = s;
                   return true;
               }
           }
           return false;
        }
        
        //390 480
        @Override
        public void execute() {
           
           if (FURNACE.isOnScreen()){
               FURNACE.interact("Smelt");
               Status = "Opening smith menu";
               Time.sleep(2800);
              if(INSTANCE == 2){ Mouse.getInstance().click(460,405, false);
             Status = "Selecting bar to smith";
             Time.sleep(1000);
              Mouse.getInstance().click(460, 480, true);
              Time.sleep(1500);
              Keyboard.getInstance().sendKeys("99");
              Status = "Smithing bars";
              Time.sleep(6500);
              } else if (INSTANCE == 3){
                  Mouse.getInstance().click(400, 406, false);
                 Status = "Selecting bar to smith";
                 Time.sleep(1000);
                  Mouse.getInstance().click(390, 480, true);
                  Time.sleep(1500);
                  Keyboard.getInstance().sendKeys("99");
                  Status = "Smithing bars";
                  Time.sleep(9000);
              } else if (INSTANCE == 4){
                 Mouse.getInstance().click(346, 406, false);
                 Status = "Selecting bar to smith";
                 Time.sleep(1000);
                  Mouse.getInstance().click(340, 480, true);
                  Time.sleep(1500);
                  Keyboard.getInstance().sendKeys("99");
                  Status = "Smithing bars";
                  Time.sleep(12000);
                  
              } else if (INSTANCE == 5) {
                  
                   Mouse.getInstance().click(233, 406, false);
                 Status = "Selecting bar to smith";
                 Time.sleep(1000);
                  Mouse.getInstance().click(233, 480, true);
                  Time.sleep(1500);
                  Keyboard.getInstance().sendKeys("99");
                  Status = "Smithing bars";
                  Time.sleep(16000);
                  
              } else if (INSTANCE == 6) {
                  
                     Mouse.getInstance().click(60, 406, false);
                 Status = "Selecting bar to smith";
                 Time.sleep(1000);
                  Mouse.getInstance().click(60, 480, true);
                  Time.sleep(1500);
                  Keyboard.getInstance().sendKeys("99");
                  Status = "Smithing bars";
                  Time.sleep(16000);
                  
              } 
         
           } else if (!FURNACE.isOnScreen()) {
               Camera.turnTo(FURNACE);
               
           }
         }
        }
      
    
    public class TeleportBankOneIngredient implements Strategy {
       
        
        @Override
        public boolean activate() {
           
            return (Players.getLocal().getAnimation() == -1
                   && Players.getLocal().getLocation().getX() < 3000                  
                   && Inventory.getCount(PRODUCT) > 0
                   && Inventory.getCount(INGREDIENT) == 0
                   
                   );
            
           
        } 

        @Override
        public void execute() {
           
        
             if (!Tab.MAGIC.isOpen()) {
                      Point p2 = new Point(742, 187);
                      Mouse.getInstance().click(p2);
                      Status = "Opening mages book";      
                      sleep(600);
        } 
           
                      
             else if(Tab.MAGIC.isOpen()) {
                
                Point p3 = new Point(570, 237);
                 Mouse.getInstance().click(p3);
                 Status = "Teleporting to bank";
                 Camera.setRotation(180);
                 Time.sleep(3000);
                 
             }
        
        }
     
  }
    
   
    
      public class TeleportBankRune implements Strategy {
       
        
        @Override
        public boolean activate() {
           
            return (Players.getLocal().getAnimation() == -1
                   && Players.getLocal().getLocation().getX() < 3000                  
                   && Inventory.getCount(PRODUCT) > 0
                   && Inventory.getCount(INGREDIENT) == 0
                   && Inventory.getCount(INGREDIENT2) == 0
                   );
            
           
        } 

        @Override
        public void execute() {
           
        
             if (!Tab.MAGIC.isOpen()) {
                      Point p2 = new Point(742, 187);
                      Mouse.getInstance().click(p2);
                      Status = "Opening mages book";      
                      sleep(600);
        } 
           
                      
             else if(Tab.MAGIC.isOpen()) {
                
                Point p3 = new Point(570, 237);
                 Mouse.getInstance().click(p3);
                 Status = "Teleporting to bank";
                 Camera.setRotation(180);
                 Time.sleep(3000);
                 
             }
        
        }
     
  }
      
      
      
      public class TeleportFurnaceOneIngredient implements Strategy {

        @Override
        public boolean activate() {
            if (Players.getLocal().getLocation().getX() > 3000){
                return (Players.getLocal().getAnimation() ==-1
                     && Inventory.getCount(PRODUCT) == 0
                     && Inventory.getCount(INGREDIENT) >0
                   );
            }
          return true;
        }
        
        @Override
        public void execute() {
          
                 
                 if (!Tab.MAGIC.isOpen()
                      && Players.getLocal().getLocation().getX() > 3000) {
                      Point p2 = new Point(742, 187);
                      Mouse.getInstance().click(p2);
                      Status = "Opening mages book";      
                      sleep(600);
                 }
                 else if(Tab.MAGIC.isOpen() 
                         && Players.getLocal().getLocation().getX() > 3000){
                 Tab.MAGIC.open();
        
                    Point p4 = new Point(715, 285);
                    Mouse.getInstance().click(p4);
                    Status = "Opening skill teleports";
                    Time.sleep(2000);
                    Point p5 = new Point (257, 431);
                    Mouse.getInstance().click(p5);
                    Time.sleep(3000);
                 } 
          
          
                   }  
             }
      
       
       public class TeleportFurnaceRune implements Strategy {

        @Override
        public boolean activate() {
            if (Players.getLocal().getLocation().getX() > 3000){
                return (Players.getLocal().getAnimation() ==-1
                     && Inventory.getCount(PRODUCT) == 0
                     && Inventory.getCount(INGREDIENT) >0
                     && Inventory.getCount(INGREDIENT2) >= Inventory.getCount(INGREDIENT) 
                        
                   );
            }
          return true;
        }
        
        @Override
        public void execute() {
          
                 
                 if (!Tab.MAGIC.isOpen()
                      && Players.getLocal().getLocation().getX() > 3000) {
                      Point p2 = new Point(742, 187);
                      Mouse.getInstance().click(p2);
                      Status = "Opening mages book";      
                      sleep(600);
                 }
                 else if(Tab.MAGIC.isOpen() 
                         && Players.getLocal().getLocation().getX() > 3000){
                 Tab.MAGIC.open();
        
                    Point p4 = new Point(715, 285);
                    Mouse.getInstance().click(p4);
                    Status = "Opening skill teleports";
                    Time.sleep(2000);
                    Point p5 = new Point (257, 431);
                    Mouse.getInstance().click(p5);
                    Time.sleep(3000);
                 } 
          
          
                   }  
             }
     public class DepositMethod implements Strategy {

        @Override
        public boolean activate() {
            if (Players.getLocal().getLocation().getX() > 3000){
                 return( Inventory.getCount(PRODUCT) > 0 
                         && Players.getLocal().getAnimation() == -1
                         
                    );
             
              }                
              
                return true;       
        }        
                
                
   
        @Override
        public void execute() {
             SceneObject[] BOOTH = SceneObjects.getNearest(2213);
             SceneObject A = BOOTH[0];
           if (!Bank.isOpen()
                   && A.isOnScreen()
                   && Players.getLocal().getAnimation() ==-1
                   ) {
                 A.interact("Use");
                 Time.sleep(3000);
                 
           } else if(!A.isOnScreen()) {
               Camera.turnTo(A, false);
               
           }   else if (Bank.isOpen()) {
               Bank.depositAll();
               Time.sleep(850);
           } 
        }    
     }
     
     
   
     public class WithdrawMethodOneIngredient implements Strategy {

        @Override
        public boolean activate() {
               if (Players.getLocal().getLocation().getX() > 3000){
                 return( Inventory.getCount(PRODUCT) == 0 
                         && Inventory.getCount(INGREDIENT) == 0
                         && Players.getLocal().getAnimation() == -1
                         
                    );
             
              }                
              
                return true; 
        }

        @Override
        public void execute() {
             SceneObject[] BOOTH = SceneObjects.getNearest(2213);
             SceneObject A = BOOTH[0];
           if (!Bank.isOpen()
                   && A.isOnScreen()
                   && Players.getLocal().getAnimation() ==-1
                   ) {
                 A.interact("Use");
                 Time.sleep(3000);
                 
           } else if(!A.isOnScreen()) {
               Camera.turnTo(A, false);
               
           }   else if (Bank.isOpen()) {
               Bank.withdraw(INGREDIENT, 99);
               Time.sleep(850);
               Bank.close();
               Time.sleep(1250);
               
           } 
        } 
        }
         
          public class WithdrawMethodRune implements Strategy {

        @Override
        public boolean activate() {
               if (Players.getLocal().getLocation().getX() > 3000){
                 return( Inventory.getCount(PRODUCT) <= 2
                         && Inventory.getCount(INGREDIENT) == 0
                         && Players.getLocal().getAnimation() == -1
                         
                    );
             
              }                
              
                return true; 
        }

        @Override
        public void execute() {
             SceneObject[] BOOTH = SceneObjects.getNearest(2213);
             SceneObject A = BOOTH[0];
           if (!Bank.isOpen()
                   && A.isOnScreen()
                   && Players.getLocal().getAnimation() ==-1
                   ) {
                 A.interact("Use");
                 Time.sleep(3000);
                 
           } else if(!A.isOnScreen()) {
               Camera.turnTo(A, false);
               
           }   else if (Bank.isOpen() && INSTANCE == 2 ) {
               Bank.withdraw(INGREDIENT, 5);
               Time.sleep(750);
               Bank.withdraw (INGREDIENT2, 20);
               Time.sleep(750);
               Bank.close();
               Time.sleep(1250);
               
           } else if(INSTANCE == 3){
               
               Bank.withdraw(INGREDIENT, 7);
               Time.sleep(750);
               Bank.withdraw (INGREDIENT2, 21);
               Time.sleep(750);
               Bank.close();
               Time.sleep(1250);
           } else if (INSTANCE == 4) {
               
                Bank.withdraw(INGREDIENT, 9);
               Time.sleep(750);
               Bank.withdraw (INGREDIENT2, 18);
               Time.sleep(750);
               Bank.close();
               Time.sleep(1250);
           } else if (INSTANCE == 5) {
               
               Bank.withdraw(INGREDIENT, 14);
               Time.sleep(750);
               Bank.withdraw (INGREDIENT2, 14);
               Time.sleep(750);
               Bank.close();
               Time.sleep(1250);
               
           } else if (INSTANCE == 6) {
               
               Bank.withdraw(INGREDIENT, 14);
               Time.sleep(750);
               Bank.withdraw (INGREDIENT2, 14);
               Time.sleep(750);
               Bank.close();
               Time.sleep(1250);
               
           } 
        } 
        }
       
          
         
    @Override
    public void onFinish() {

    }

       

 

    public void paint(Graphics g1) {
       if (guiWait == false)
       {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(312, 345, 183, 114, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(312, 345, 183, 114, 16, 16);
        g.setFont(font1);
        g.drawString("iSmith", 370, 365);
        g.setFont(font2);
        g.drawString("Time ran: " +runTime(startTime), 317, 381);
        g.drawString("Current bar: " + BARTYPE, 317, 397);
        g.drawString("Bars smited: " + BARS, 317, 415);
        g.drawString("XP gained: " + XPGAINED, 317, 434);
        g.drawString("Version: " + "2.0", 317, 452);
        
       }
        
    }
  

 
    
@Override
public void messageReceived(MessageEvent m) {
        // TODO Auto-generated method stub
       
        if (m.getMessage().contains("You received 1 PkHonor point, you now")) {
        PKP += 1;
        } else {
        if (m.getMessage().contains("gold")) {
            BARS += 1;
            XPGAINED += 1450;
 
       
                                } else {
            if (m.getMessage().contains("rune")) {
            BARS += 1;
            XPGAINED += 10000;
        } else {
                 if (m.getMessage().contains("adamant")) {
            BARS += 1;
            XPGAINED += 6000;
            } else if(m.getMessage().contains("iron")) {
                
            BARS += 1;
            XPGAINED += 6000;                
                
            } else if (m.getMessage().contains("mithril")){
                XPGAINED += 3500;
                BARS += 1;
                
                
            } else if (m.getMessage().contains("steel")){
                XPGAINED += 1450;
                BARS += 1;
                
                
            } else if (m.getMessage().contains("bronze")){
                XPGAINED += 300;
                BARS += 1;
                
                
            }
                        }
                  }
          }
}
public static class iSmithGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
           * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					iSmithGUI frame = new iSmithGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public iSmithGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 434, 282);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Welcome", null, panel, null);
		
		JLabel lblWelcomeToIsmith = new JLabel("Welcome to iSmith");
		lblWelcomeToIsmith.setFont(new Font("Arial", Font.BOLD, 32));
		panel.add(lblWelcomeToIsmith);
		
		JLabel lblPleaseFillOut = new JLabel("Please fill out the smithing tab then select \"Start\"");
		lblPleaseFillOut.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.add(lblPleaseFillOut);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Smithing", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JCheckBox checkEnableSmithing = new JCheckBox("Enable Smithing");
		checkEnableSmithing.setBounds(6, 58, 181, 35);
		checkEnableSmithing.setFont(new Font("Arial", Font.PLAIN, 22));
		panel_1.add(checkEnableSmithing);
		
		JLabel lblSmithing = new JLabel("Smithing");
		lblSmithing.setFont(new Font("Arial", Font.BOLD, 30));
		lblSmithing.setBounds(123, 11, 225, 40);
		panel_1.add(lblSmithing);
		
		final JComboBox oreToSmelt = new JComboBox();
		oreToSmelt.setModel(new DefaultComboBoxModel(new String[] { "SMITHING NOT ENABLED", "Rune bar", "Adamant bar", "Mithril bar", "Gold bar", "Iron bar", "Steel bar", "Bronze bar", "Cannon ball"}));
		oreToSmelt.setEnabled(false);
		oreToSmelt.setFont(new Font("Arial", Font.BOLD, 12));
		oreToSmelt.setBounds(59, 100, 162, 25);
		panel_1.add(oreToSmelt);
		
		JLabel lblSmith = new JLabel("Smith");
		lblSmith.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSmith.setBounds(6, 103, 46, 14);
		panel_1.add(lblSmith);
		
		JLabel lblBar = new JLabel("bar");
		lblBar.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBar.setBounds(226, 105, 46, 14);
		panel_1.add(lblBar);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLocation.setBounds(6, 136, 82, 25);
		panel_1.add(lblLocation);
		
		final JComboBox locationSmith = new JComboBox();
		locationSmith.setEnabled(false);
		locationSmith.setModel(new DefaultComboBoxModel(new String[] {"SMITHING NOT ENABLED", "Varrock"}));
		locationSmith.setFont(new Font("Arial", Font.BOLD, 12));
		locationSmith.setBounds(84, 136, 162, 25);
		panel_1.add(locationSmith);
		
		final String chosen = oreToSmelt.getSelectedItem().toString();
		
		checkEnableSmithing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkEnableSmithing.isSelected()) {
					
					oreToSmelt.setEnabled(true);
					locationSmith.setEnabled(true);
					locationSmith.setModel(new DefaultComboBoxModel(new String[] {"Varrock"}));
					oreToSmelt.setModel(new DefaultComboBoxModel(new String[] {"Rune bar", "Adamant bar", "Mithril bar", "Gold bar", "Iron bar", "Steel bar", "Bronze bar"}));
				} else if (!checkEnableSmithing.isSelected()) {
					locationSmith.setEnabled(false);
					oreToSmelt.setEnabled(false);
					locationSmith.setModel(new DefaultComboBoxModel(new String[] {"SMITHING NOT ENABLED"}));
					oreToSmelt.setModel(new DefaultComboBoxModel(new String[] {"SMITHING NOT ENABLED"}));
				}
			
			}
		});
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Start", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
                    
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      String chosen = oreToSmelt.getSelectedItem().toString();
			if(oreToSmelt.getSelectedItem().equals("Gold bar")) {
                             INSTANCE = 1;
                             INGREDIENT = 444;
                             BARTYPE = "Gold bar";                            
                             PRODUCT = 2357;
                         } else if (chosen.equals("Rune bar")) {
                            
                             BARTYPE = "Rune bar";
                             PRODUCT = 2363;
                             INGREDIENT = 451;
                             INGREDIENT2 = 453;
                             INSTANCE = 2;
                         
                         } else if (chosen.equals("Iron bar")){
                             BARTYPE = "Iron bar";
                             PRODUCT = 2351;
                             INGREDIENT = 440;
                             INSTANCE = 1;
                         } else if (chosen.equals("Adamant bar")) {
                             BARTYPE = "Adamant bar";
                             PRODUCT = 2361;
                             INGREDIENT = 449;
                             INGREDIENT2 = 453;
                             INSTANCE = 3;
                         } else if (chosen.equals("Mithril bar")){
                             BARTYPE = "Mithril bar";
                             PRODUCT = 2359;
                             INGREDIENT = 447;
                             INGREDIENT2 = 453;
                             INSTANCE = 4;
                             //3500
                         } else if (chosen.equals("Steel bar")) {
                             
                              BARTYPE = "Steel bar";
                             PRODUCT = 2353;
                             INGREDIENT = 440;
                             INGREDIENT2 = 453;
                             INSTANCE = 5;
                             //1450
                             
                         } else if (chosen.equals("Bronze bar")){
                              
                              BARTYPE = "Bronze bar";
                             PRODUCT = 2349;
                             INGREDIENT = 436;
                             INGREDIENT2 = 438;
                             INSTANCE = 6;
                             //300
                             
                         } else if (chosen.equals("Cannon ball")) {
                              BARTYPE = "Cannon ball";
                              PRODUCT =  2;
                              INGREDIENT = 2353;
                              AMMO_MOULD = 4;
                              INSTANCE = 7;
                             
                         }
                         guiWait = false;
                         g.dispose();
                        }
                
                
                        });
        
			

		startButton.setBounds(160, 196, 91, 32);
		startButton.setFont(new Font("Arial", Font.BOLD, 13));
		panel_3.add(startButton);
		
		JLabel lblCheckTheFollowing = new JLabel("Check the following before starting");
		lblCheckTheFollowing.setFont(new Font("Arial", Font.BOLD, 20));
		lblCheckTheFollowing.setBounds(45, 0, 359, 46);
		panel_3.add(lblCheckTheFollowing);
		
		JLabel lblSmithing_1 = new JLabel("Smithing");
		lblSmithing_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSmithing_1.setBounds(160, 52, 159, 22);
		panel_3.add(lblSmithing_1);
		
		JLabel lblInventoryIs = new JLabel("\u2022 Inventory is empty on start");
		lblInventoryIs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInventoryIs.setBounds(115, 79, 193, 14);
		panel_3.add(lblInventoryIs);
		
		JLabel lblOresAnd = new JLabel("\u2022 Ore's and bars are visible in bank");
		lblOresAnd.setFont(new Font("Arial", Font.PLAIN, 14));
		lblOresAnd.setBounds(114, 85, 255, 32);
		panel_3.add(lblOresAnd);
	}
      }
}
