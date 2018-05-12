package Levels;

import acm.graphics.GImage;
import acm.graphics.GPoint;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Level {
	public static final double Brick_Width = 32;
	double REFERENCE_POINT, WIDTH, HEIGHT;
	public ArrayList<GPoint> QuestionBlock = new ArrayList<>();
	public HashMap<GPoint, Integer> Ground_High = new HashMap<>();
	public HashMap<GPoint, Integer> Ground_Low = new HashMap<>();
	public HashMap<GImage, Double> Tube = new HashMap<>();
	public HashMap<GPoint, Integer> Steps = new HashMap<>();

	public Level(double REFERENCE_POINT, double WIDTH, double HEIGHT) {
		this.REFERENCE_POINT = REFERENCE_POINT;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
}