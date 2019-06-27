import java.awt.*;
import java.util.Timer;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Random;

public class tetrisv2 extends Frame
{
	public static void main(String[] args){new tetrisv2();}
	tetrisv2()
	{
		super("Assignment-2");
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
		);
		setSize (700, 700); // Set the Window size
		add("Center", new CvTetris());
		setVisible(true);
	}
}

class CvTetris extends Canvas
{ 
	Element mainElement;
	Timer timer;
	Random random = new Random();
	int centerX, centerY;
	float pixelSize, rWidth = 240.0F;
	float rHeight = 240.0F, xP, yP, xM, yM;
	boolean mainArea=false;
	boolean nextMainArea=false;
	int max=7;
	int min=1;
	int diff = max-min+1;
	int mainrandom = random.nextInt(max)%(diff) + min;
	int nextrandom = random.nextInt(max)%(diff) + min;
	int nextAreaWidth=8;
	int nextAreaHeight=4;
	int nextarea[][]= new int[nextAreaWidth][nextAreaHeight];
	int mainElementx=2;
	int mainElementy=1;
	boolean onFloor=false;
	int playArea[][]= new int[10][21];
	int orientation=0;
	
	static class GameTime extends TimerTask {
		CvTetris objCv;
		GameTime(CvTetris objCv){
			super();
			this.objCv=objCv;
		}
		@Override
		public void run() {
			objCv.fall();
			
		}
	}
	
	
  public void setnextMainArea(boolean nextMainArea) {
  		if(this.nextMainArea != nextMainArea) 
  		{
  			if(nextMainArea) 
  			{
  				if(timer!=null) {
  				timer.cancel();
  				timer=null;
  				}
  			}else 
  			{
  				timer = new Timer();
  				timer.scheduleAtFixedRate(new GameTime(this), 0, 800);
  			}
  			this.nextMainArea = nextMainArea;
  		}
		
	}

  
  void mouseScrollDriver(int mouseDirection)
  {	
		if(onFloor) return; //when piece touch the ground
		orientation+=mouseDirection*-1; //it will change the orientation during ScrollUp or ScrollDown
		orientation= orientation<0?orientation+4:orientation%4;
		mainElement.setLocation(mainElementx, mainElementy);
		mainElement.setElements();
		boolean againRepaint=true;
		for(int x=0;x<4;x++) {
			for(int y=0;y<2;y++){
				if(y==0 && mainElement.Elementarray[x][y]<=-1) 
				{
					againRepaint=false;
					orientation+=mouseDirection;
					orientation= orientation<0?orientation+4:orientation%4;
				}
				else if(y==1 && mainElement.Elementarray[x][y]>=19) 
				{
					againRepaint=false;
					orientation+=mouseDirection;
					orientation= orientation<0?orientation+4:orientation%4;
				}
				}
			}
	
		if(againRepaint) repaint();
		

}
  
  void mouseButtonDriver(int buttonCode) // +1 for left button and -1 for right button
  {
	  if(onFloor) return;
		
		mainElementx=mainElementx+buttonCode*-1;
		mainElement.setLocation(mainElementx, mainElementy);
		mainElement.setElements();
		boolean againRepaint=true;
		for(int x=0;x<4;x++) {
			for(int y=0;y<2;y++){
				if(y==0 && mainElement.Elementarray[x][y]<=-1) 
					{
						againRepaint=false;
						mainElementx=mainElementx+buttonCode;
					}
				else if(y==1 && mainElement.Elementarray[x][y]>=19) 
					{
						againRepaint=false;
						mainElementx=mainElementx+buttonCode;
					}
				}
			}
	
		if(againRepaint) repaint();
  }
	  
	CvTetris()
	{	
		addMouseWheelListener(new MouseAdapter() {
          @Override
          public void mouseWheelMoved(MouseWheelEvent evt) {
              super.mouseWheelMoved(evt);
              if(mainArea)
                  return;
              if (evt.getWheelRotation() < 0)
            	  mouseScrollDriver(-1);
              else
                  mouseScrollDriver(1); // for DownRotation
          }
      });
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent evt)
			{
				xP = evt.getX(); yP = evt.getY();
				int button=evt.getButton();
				if((xP<iX(80))&&(xP>iX(20))&&(yP<iY(-100))&&(yP>iY(-80)))
				System.exit(0);
				else if(button==MouseEvent.BUTTON1)
					mouseButtonDriver(1);
				else if(button==MouseEvent.BUTTON3)
					mouseButtonDriver(-1);
					
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseMoved(MouseEvent evtM) 
			{
				if(onFloor) return;
			
				xM = evtM.getX(); yM = evtM.getY();
				if((xM<iX(0))&&(xM>iX(-100))&&(yM<iY(-100))&&(yM>iY(100)))
				{
					mainArea=true;
					if(nextMainArea!=mainArea) 
					{
						repaint();
						setnextMainArea(mainArea);
					}
				}
				else
				{
					mainArea=false;
					if(nextMainArea!=mainArea)
					{
						repaint();
						setnextMainArea(mainArea);
					}
				}
			}
		});
		timer = new Timer();
		timer.scheduleAtFixedRate(new GameTime(this), 0, 500);
	}
	public void fall() {
		if(mainElement==null) {
			return;
		}
		if(onFloor) return;
		mainElementy=mainElementy+1;
		mainElement.setLocation(mainElementx, mainElementy);
		mainElement.setElements();
		mainElement.setDirection(orientation);
		System.out.println(playArea.length+" "+playArea[0].length);
		System.out.println(mainElement.Elementarray[0][0]+" "+mainElement.Elementarray[0][1]);
		if(playArea[mainElement.Elementarray[0][0]][mainElement.Elementarray[0][1]]!=0) {
			onFloor=true;
			mainElementy=mainElementy-1;
			return;
		}
		
		if(playArea[mainElement.Elementarray[1][0]][mainElement.Elementarray[1][1]]!=0) {
				onFloor=true;
				mainElementy=mainElementy-1;
				return;	
		}
		if(playArea[mainElement.Elementarray[2][0]][mainElement.Elementarray[2][1]]!=0) {
			onFloor=true;
			mainElementy=mainElementy-1;
			return;	
		}
		if(playArea[mainElement.Elementarray[3][0]][mainElement.Elementarray[3][1]]!=0){
			onFloor=true;
			mainElementy=mainElementy-1;
			return;	
		}

		boolean paintMarker=true;

		for(int x=0;x<4;x++) {
			for(int y=0;y<2;y++) {
				if(y==0 && mainElement.Elementarray[x][y]<0) 
				{
					paintMarker=false;
					mainElementy=mainElementy-1;
				}
				else if(y==1 && mainElement.Elementarray[x][y]>19) 
				{
					paintMarker=false;
					onFloor=true;
					mainElementy=mainElementy-1;
				}
				
			}
		}
		if(paintMarker) repaint();		
	}
	float fx(int x){return (x - centerX) * pixelSize;}
	float fy(int y){return (centerY - y) * pixelSize;}
	int iX(float x){return Math.round(centerX + x/pixelSize);}
	int iY(float y){return Math.round(centerY - y/pixelSize);}
	int iElemLength(float l) {return Math.round(l/pixelSize);}
	int iFont(float f) {return Math.round(f/pixelSize);}
	
	void initgr()
	{
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
		centerX = maxX/2; centerY = maxY/2;
		
	}
	void nextAreaSetter(int type, int elementX, int elementY,int x1,int x2,int x3,int x4,int y1,int y2,int y3,int y4)
	{
		nextarea[elementX+x1][elementY+y1]=type;
		nextarea[elementX+x2][elementY+y2]=type;
		nextarea[elementX+x3][elementY+y3]=type;
		nextarea[elementX+x4][elementY+y4]=type;
	}
	
	void colorSetter(int area,Graphics g)
	{
		System.out.print(area);
		if(area==1)
			g.setColor(new Color(255,255,0)); //Yellow Color
		else if(area==2)
			g.setColor(new Color(112,48,160));//Purple Color
		else if(area==3)
			g.setColor(new Color(0,112,192)); //Blue Color1 (Dark)
		else if(area==4)
			g.setColor(new Color(255,0,0)); //Red Color
		else if(area==5)
			g.setColor(new Color(0,176,80)); // Green Dark
		else if(area==6)
			g.setColor(new Color(255,192,0)); // Yellow Dark
		else if(area==7)
			g.setColor(new Color(0,176,240)); // Blue Color	
	}
	
	void setPlayArea(int val)
	{
		for(int i=0;i<10;i++)
		{
			playArea[i][20] = val;
			if(i==5 ||i==6||i==7||i==8)
			{
				playArea[i][19] = val;	
			}
		}
	}
	void setMainArea(int val,int[][] mainarea)
	{
		for(int i=5;i<9;i++) mainarea[i][19]=val;
	}
	public void paint(Graphics g)
	{
		initgr();
		// Draw Main Rectangle
		g.drawLine(iX(-100), iY(100), iX(-100), iY(-100));
		g.drawLine(iX(0),iY(100), iX(0), iY(-100));
		g.drawLine(iX(-100), iY(100), iX(0),iY(100));
		g.drawLine(iX(-100), iY(-100), iX(0), iY(-100));
		//Draw Small Rectangle(Next Area)
		g.drawLine(iX(20), iY(100), iX(20), iY(60));
		g.drawLine(iX(100),iY(100), iX(100), iY(60));
		g.drawLine(iX(20), iY(100), iX(100),iY(100));
		g.drawLine(iX(20), iY(60), iX(100), iY(60));
		//Quit Button
		g.drawLine(iX(20), iY(-80), iX(20), iY(-100));
		g.drawLine(iX(80),iY(-80), iX(80), iY(-100));
		g.drawLine(iX(20), iY(-80), iX(80),iY(-80));
		g.drawLine(iX(20), iY(-100), iX(80), iY(-100));
		//define main area with Width=20 and Height=10
		int mainAreaWidth=10;
		int mainAreaHeight=20;
	    int mainarea[][]= new int[mainAreaWidth][mainAreaHeight];
		//set the Random element
		this.mainElement=new Element(mainrandom);
		mainElement.setLocation(mainElementx, mainElementy);
		int type=mainElement.typeInfo();
		mainElement.setDirection(orientation);
		mainElement.setElements();
			mainarea[mainElement.Elementarray[0][0]][mainElement.Elementarray[0][1]]=type;
			mainarea[mainElement.Elementarray[1][0]][mainElement.Elementarray[1][1]]=type;
			mainarea[mainElement.Elementarray[2][0]][mainElement.Elementarray[2][1]]=type;
			mainarea[mainElement.Elementarray[3][0]][mainElement.Elementarray[3][1]]=type;
		
		setPlayArea(7);
		setMainArea(7,mainarea);
		//draw pieces in Main(PlayArea);
		for(int x=0; x<mainAreaWidth; x++)
			for(int y=0; y<mainAreaHeight; y++)
			{
				if(mainarea[x][y]!=0) {
					int Elementx=-100+10*x;
					int Elementy=100-10*y;
					colorSetter(mainarea[x][y],g);
					g.fillRect(iX(Elementx), iY(Elementy), iElemLength(10), iElemLength(10));
					g.setColor(Color.black);
					g.drawRect(iX(Elementx), iY(Elementy), iElemLength(10), iElemLength(10));
				}
			}
		
		Element nextElement=new Element(nextrandom);
		nextElement.setLocation(2, 1);
		int nexttype=nextElement.typeInfo();
		int nextElementx=nextElement.xLoc();
		int nextElementy=nextElement.yLoc();
		
		if(nexttype==1)
			nextAreaSetter(nexttype,nextElementx,nextElementy,0,1,-1,0,0,0,1,1);
		else if (nexttype==2)
			nextAreaSetter(nexttype,nextElementx,nextElementy,0,1,1,2,0,0,1,1);
		else if (nexttype==3)
			nextAreaSetter(nexttype,nextElementx,nextElementy,0,2,0,1,0,1,1,1);
		else if (nexttype==4)
			nextAreaSetter(nexttype,nextElementx,nextElementy,2,2,0,1,0,1,1,1);
		else if (nexttype==5)
			nextAreaSetter(nexttype,nextElementx,nextElementy,0,1,0,1,0,0,1,1);
		else if (nexttype==6)
			nextAreaSetter(nexttype,nextElementx,nextElementy,1,2,0,1,0,1,1,1);
		else if (nexttype==7)
			nextAreaSetter(nexttype,nextElementx,nextElementy,3,2,0,1,0,0,0,0);

		//draw Pieces in small area(next Area);
		for(int smallX=0; smallX<nextAreaWidth; smallX++)
			for(int smallY=0; smallY<nextAreaHeight; smallY++)
			{
				if(nextarea[smallX][smallY]!=0) {
					int nextx=20+10*smallX;
					int nexty=100-10*smallY;
					colorSetter(nextarea[smallX][smallY],g);
					g.fillRect(iX(nextx), iY(nexty), iElemLength(10), iElemLength(10));
					g.setColor(Color.black);
					g.drawRect(iX(nextx), iY(nexty), iElemLength(10), iElemLength(10));
				}
			}

		Font myFont = new Font( "Arial", Font.BOLD, iFont(12) ); //Set the Font Size and Type
		g.setFont(myFont);
		g.drawString("Level:     1", iX(19), iY(19));
		g.drawString("Lines:     0", iX(19), iY(0));
		g.drawString("Score:    0", iX(19), iY(-19));
		g.drawString("QUIT", iX(34), iY(-96));
		
		if(mainArea)
		{
			//Draw Pause Button
			g.setColor(Color.blue);
			g.drawLine(iX(-75), iY(8), iX(-75), iY(-8));
			g.drawLine(iX(-30),iY(8), iX(-30), iY(-8));
			g.drawLine(iX(-75), iY(8), iX(-30),iY(8));
			g.drawLine(iX(-75), iY(-8), iX(-30), iY(-8));
			g.setColor(Color.blue);
			g.drawString("PAUSE", iX(-73), iY(-5));
		}

	} 
}