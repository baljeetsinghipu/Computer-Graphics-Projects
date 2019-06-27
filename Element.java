public class Element{
	int type;
	public Element(int type) {
		this.type=type;
	}
	int x; int y;
	//set Element location
	public void setLocation(int x_point, int y_point) {
		x=x_point;
		y=y_point;
	}
	public int xLoc() {
		return x;
	}
	public int yLoc() {
		return y;
	}
	public int typeInfo() {
		return type;
	}
	int orientation;
	public void setDirection(int orientation) {
		this.orientation=orientation;
	}
	
	int[][] Elementarray=new int[4][2];
	void elementArraySetter(int e1, int e2,int e3,int e4,int e5,int e6,int e7,int e8)
	{
		Elementarray[0][0]=e1; Elementarray[0][1]=e2; Elementarray[1][0]=e3; Elementarray[1][1]=e4; Elementarray[2][0]=e5; Elementarray[2][1]=e6; Elementarray[3][0]=e7; Elementarray[3][1]=e8;
	}
	public void setElements()
	{
		switch (type) 
		{
			case 1:
				switch (orientation) {
				case 0:
					elementArraySetter(x,y,x,y-1,x-1,y,x+1,y-1);
					break;
				case 1:
					elementArraySetter(x,y,x,y-1,x+1,y,x+1,y+1);
					break;
				case 2:
					elementArraySetter(x,y-1,x,y-2,x-1,y-1,x+1,y-2);
					break;
				case 3:
					elementArraySetter(x-1,y,x-1,y-1,x,y,x,y+1);
					break;
				}

				break;
			case 2:
				switch (orientation) {
				case 0:
					elementArraySetter(x,y,x,y-1,x-1,y-1,x+1,y);
					break;
				case 1:
					elementArraySetter(x,y,x,y+1,x+1,y,x+1,y-1);
					break;
				case 2:
					elementArraySetter(x,y+1,x,y,x-1,y,x+1,y+1);
					break;
				case 3:
					elementArraySetter(x-1,y,x-1,y+1,x,y,x,y-1);
					break;
				}
				break;
			case 3:
				switch (orientation) {
				case 0:
					elementArraySetter(x,y,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 1:
					elementArraySetter(x+1,y,x+2,y,x+1,y+2,x+1,y+1);
					break;
				case 2:
					elementArraySetter(x+2,y+2,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 3:
					elementArraySetter(x+1,y,x,y+2,x+1,y+2,x+1,y+1);
					break;
				}
				break;
			case 4:
				switch (orientation) {
				case 0:
					elementArraySetter(x+2,y,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 1:
					elementArraySetter(x+1,y,x+2,y+2,x+1,y+2,x+1,y+1);
					break;
				case 2:
					elementArraySetter(x,y+2,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 3:
					elementArraySetter(x+1,y,x,y,x+1,y+2,x+1,y+1);
					break;
				}
				break;
			case 5:
				switch (orientation) {
				case 0:
					elementArraySetter(x,y,x+1,y,x,y+1,x+1,y+1);
					break;
				case 1:
					elementArraySetter(x,y,x+1,y,x,y+1,x+1,y+1);
					break;
				case 2:
					elementArraySetter(x,y,x+1,y,x,y+1,x+1,y+1);
					break;
				case 3:
					elementArraySetter(x,y,x+1,y,x,y+1,x+1,y+1);
					break;
				}
				break;
			case 6:
				switch (orientation) {
				case 0:
					elementArraySetter(x+1,y,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 1:
					elementArraySetter(x+1,y,x+2,y+1,x+1,y+2,x+1,y+1);
					break;
				case 2:
					elementArraySetter(x,y+1,x+2,y+1,x+1,y+2,x+1,y+1);
					break;
				case 3:
					elementArraySetter(x,y+1,x+1,y,x+1,y+2,x+1,y+1);
					break;
				}
				break;
			case 7:
				switch (orientation) {
				case 0:
					elementArraySetter(x+3,y+1,x+2,y+1,x,y+1,x+1,y+1);
					break;
				case 1:
					elementArraySetter(x+3,y,x+3,y+1,x+3,y+2,x+3,y+3);
					break;
				case 2:
					elementArraySetter(x+3,y+2,x+2,y+2,x,y+2,x+1,y+2);
					break;
				case 3:
					elementArraySetter(x+2,y,x+2,y+1,x+2,y+2,x+2,y+3);
					break;
				}
				break;
		}
	}
}