package edu.wm.cs.cs301.memorygame.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import edu.wm.cs.cs301.memorygame.model.AppColors;
import edu.wm.cs.cs301.memorygame.model.GamePiece;
import edu.wm.cs.cs301.memorygame.model.MemoryGame;

public class MemoryGameGridPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final int topMargin, leftMargin, letterWidth;

	private final Insets insets;

	private final Rectangle[][] grid;

	private final MemoryGame model;

	public MemoryGameGridPanel(MemoryGameFrame view, MemoryGame model) {
		int width = this.getPreferredSize().width;
		this.model = model;
		this.topMargin = 0;
		this.letterWidth = 64;
		this.insets = new Insets(0, 6, 6, 6);

		int wordWidth = (letterWidth + insets.right) * model.getCol();
		this.leftMargin = (width - wordWidth) / 2;
		int height = (letterWidth + insets.bottom) * model.getRow()
				+ 2 * topMargin;
		this.setPreferredSize(new Dimension(width, height));

		this.grid = calculateRectangles();
	}

	private Rectangle[][] calculateRectangles() {
		Rectangle[][] grid = new Rectangle[model.getRow()][model
				.getCol()];

		int x = leftMargin;
		int y = topMargin;

		for (int row = 0; row < model.getRow(); row++) {
			for (int column = 0; column < model.getCol(); column++) {
				grid[row][column] = new Rectangle(x, y, letterWidth,
						letterWidth);
				x += letterWidth + insets.right;
			}
			x = leftMargin;
			y += letterWidth + insets.bottom;
		}

		return grid;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		Font titleFont = AppFonts.getTitleFont();
		
		GamePiece[][] wordleGrid = model.getGameBoard().getBoard();
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				Rectangle r = grid[row][column];
				GamePiece memoryGameResponse = wordleGrid[row][column];
				drawOutline(g2d, r);
				drawMemoryGameResponse(g2d, memoryGameResponse, r, titleFont);
			}
		}
	}

	private void drawOutline(Graphics2D g2d, Rectangle r) {
		int x = r.x + 1;
		int y = r.y + 1;
		int width = r.width - 2;
		int height = r.height - 2;
		g2d.setColor(AppColors.OUTLINE);
		g2d.setStroke(new BasicStroke(3f));
		g2d.drawLine(x, y, x + width, y);
		g2d.drawLine(x, y + height, x + width, y + height);
		g2d.drawLine(x, y, x, y + height);
		g2d.drawLine(x + width, y, x + width, y + height);
	}

	private void drawMemoryGameResponse(Graphics2D g2d,
			GamePiece MemoryGameResponse, Rectangle r, Font titleFont) {
		if (MemoryGameResponse != null) {
			//g2d.setColor(MemoryGameResponse.getBackgroundColor());
			g2d.fillRect(r.x, r.y, r.width, r.height);
			//g2d.setColor(MemoryGameResponse.getForegroundColor());
			drawCenteredString(g2d,
					Character.toString(MemoryGameResponse.getSymbol()), r, titleFont);
		}
	}

	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	private void drawCenteredString(Graphics2D g2d, String text, Rectangle rect,
			Font font) {
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2)
				+ metrics.getAscent();

		g2d.setFont(font);
		g2d.drawString(text, x, y);
	}

}
