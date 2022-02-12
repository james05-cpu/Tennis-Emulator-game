                package com.DiverPlan;

                import javax.swing.*;
                import java.awt.*;
                import java.awt.event.*;
                import java.util.Random;

                public class GamePanel extends JPanel implements Runnable {
                    int GAME_WIDTH=1000;
                    int GAME_HEIGHT=(int)(GAME_WIDTH*(0.5555));
                    Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
                    int BALL_DIAMETER=20;
                    int PADDLE_WIDTH=20;
                    int PADDLE_HEIGHT=100;
                    Thread gameThread;
                    Paddle paddle1;
                    Paddle paddle2;
                    Ball ball;
                    Score score;
                    Image image;
                    Graphics graphics;
                    Random random;
                    GamePanel() {
                     newPaddles();
                     newBall();
                     score=new Score(GAME_WIDTH,GAME_HEIGHT);
                     setFocusable(true);
                     addKeyListener(new AL());
                     setPreferredSize(SCREEN_SIZE);
                     gameThread=new Thread(this);
                   //  gameThread.start();
                    }

                    public void newBall() {
                random=new Random();
                ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
                    }

                    public void newPaddles() {
                paddle1=new Paddle(0,((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)),PADDLE_WIDTH,PADDLE_HEIGHT,1);
                        paddle2=new Paddle((GAME_WIDTH-PADDLE_WIDTH),((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)),PADDLE_WIDTH,PADDLE_HEIGHT,2);
                    }

                    public void paint(Graphics g) {
                image=createImage(getWidth(),getHeight());
                graphics=image.getGraphics();
                draw(graphics);
                g.drawImage(image,0,0,this);
                    }

                    public void draw(Graphics g) {
                paddle1.draw(g);
                paddle2.draw(g);
                ball.draw(g);
                score.draw(g);
                    }

                    public void move() {
                paddle1.move();
                paddle2.move();
                ball.move();
                    }

                    public void checkCollision() {
                        //give 1 point and create new elements
                        if (ball.x<=0){
                            score.Player2++;
                            newPaddles();
                            newBall();
                        }
                        if (ball.x>=GAME_WIDTH-BALL_DIAMETER){
                            score.Player1++;
                            newPaddles();
                            newBall();
                        }
                     //   bounce paddles off
                        if (ball.intersects(paddle1)){
                            ball.xVelocity=Math.abs(ball.xVelocity);
                            ball.xVelocity++; //optional
                            if (ball.yVelocity>0)
                                ball.yVelocity++;
                            else
                                ball.yVelocity--;
                            ball.setXDirection(ball.xVelocity);
                            ball.setYDirection(ball.yVelocity);
                        }
                        if (ball.intersects(paddle2)){
                            ball.xVelocity=Math.abs(ball.xVelocity);
                            ball.xVelocity++; //optional
                            if (ball.yVelocity>0)
                                ball.yVelocity++;
                            else
                                ball.yVelocity--;
                            ball.setXDirection(-ball.xVelocity);
                            ball.setYDirection(ball.yVelocity);
                        }
                        //bounce top bottom
                        if (ball.y<=0)
                            ball.setYDirection(-ball.yVelocity);
                        if (ball.y>=GAME_HEIGHT-BALL_DIAMETER){
                            ball.setYDirection(-ball.yVelocity);
                        }
                //stops at edge
                        if (paddle1.y<=0)
                            paddle1.y=0;
                        if (paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
                            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
                        if (paddle2.y<=0)
                            paddle2.y=0;
                        if (paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
                            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;

                    }

                    public void run() {
                        long LastTime=System.nanoTime();
                        double amountOfTicks=60.0;
                        double ns=1000000000/amountOfTicks;
                        double delta=0;

                while (true){
                    long now=System.nanoTime();
                    delta+=(now-LastTime)/ns;
                    LastTime=now;
                    if (delta>1){
                        move();
                        checkCollision();
                        repaint();
                        delta--;
                    }
                }
                    }

                    public class AL extends KeyAdapter {
                        public void keyPressed(KeyEvent e) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
                        }
                        public void keyReleased(KeyEvent e){
                            paddle1.keyReleased(e);
                            paddle2.keyReleased(e);
                        }
                    }
                }
