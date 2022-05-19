package Code;
// Needs a package declaration to move to another folder

import com.birdbraintechnologies.Finch;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/**
 * Created by:
 * Date:
 * A starter file to use the Finch
 */

public class FinchTemplateFile implements KeyListener
{
    static final int UP = 0;
    static final int DOWN = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;

    // Instantiating the Finch object
    private final Finch myFinch = new Finch();
    private final int[][] matrix = new int[10][10];
    private int x = 0;
    private int y = 0;
    private int direction = FinchTemplateFile.RIGHT;

    public static void main(final String[] args)
    {
        Frame f = new Frame("Demo");
        f.setLayout(new FlowLayout());
        f.setSize(500, 500);
        Label l = new Label();
        l.setText("This is a demonstration");
        f.add(l);
        f.setVisible(true);
        FinchTemplateFile keyListener = new FinchTemplateFile();
        f.addKeyListener(keyListener);
        keyListener.run();
    }

    public void run()
    {
        for (int i = 0; i < 10; i++)
            Arrays.fill(matrix[i], 0);
        matrix[y][x] = 1;

        while(true)
        {
            if (isFree())
            {
                moveForward();
                if (direction == RIGHT)
                    x++;
                else if (direction == DOWN)
                    y++;
                else if (direction == LEFT)
                    x--;
                else if (direction == UP)
                    y--;

                matrix[y][x] = 1;
            } else
            {
                turnRight();
            }

            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
            System.out.println(direction);
            System.out.println();
        }

    }

    public boolean isFree()
    {
        int t_x = x;
        int t_y = y;

        if (direction == RIGHT)
            t_x++;
        else if (direction == DOWN)
            t_y++;
        else if (direction == LEFT)
            t_x--;
        else if (direction == UP)
            t_y--;

//        System.out.println(t_y + " " + t_x);

        if (t_x >= 0 && t_x <= 9 && t_y >= 0 && t_y <= 9)
            return matrix[t_y][t_x] == 0;
        else
            return false;
    }

    public void moveForward()
    {
        myFinch.setWheelVelocities(150, 150, 1000);
    }

    public void turnRight()
    {
        myFinch.setWheelVelocities(100, -100, 750);

        if (direction == RIGHT)
            direction = DOWN;
        else if (direction == DOWN)
            direction = LEFT;
        else if (direction == LEFT)
            direction = UP;
        else if (direction == UP)
            direction = RIGHT;
    }

    public void turnLeft()
    {
        myFinch.setWheelVelocities(-255, 255, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_G)
        {
            myFinch.quit();
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

