package entity;

import java.awt.*;
import java.awt.image.*;

public class Entity {

	public int worldX, worldY;
	public int speed;

	public BufferedImage normal1, normal2, up, down1, down2, left1, left2, right1, right2;
	String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;
}
