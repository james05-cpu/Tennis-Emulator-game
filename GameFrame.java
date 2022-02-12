                        package com.DiverPlan;

                        import javax.swing.*;
                        import java.awt.*;
                        import java.awt.event.ActionEvent;
                        import java.awt.event.ActionListener;

                        public class GameFrame extends JFrame{
                            GamePanel panel;
                            JMenuBar jMenuBar;
                            JMenu options,more;
                            JMenuItem start, expert,medium,normal,about,exit;
                            GameFrame(){
                                panel=new GamePanel();
                                start=new JMenuItem("Start");
                                expert =new JMenuItem("Expert");
                                medium=new JMenuItem("Medium");
                                normal=new JMenuItem("Normal");
                                about=new JMenuItem("About");
                                exit=new JMenuItem("Exit");
                                start.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.gameThread.start();
                                    }
                                });
                                about.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        new AboutMe();
                                    }
                                });
                                normal.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.PADDLE_HEIGHT=100;
                                    }
                                });
                                medium.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.PADDLE_HEIGHT=50;
                                    }
                                });
                                expert.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panel.PADDLE_HEIGHT=25;
                                    }
                                });
                                exit.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        System.exit(0);
                                    }
                                });
                                options=new JMenu("Options");
                                more=new JMenu("More");
                                more.add(about);
                                options.add(start);
                                options.add(normal);
                                options.add(medium);
                                options.add(expert);
                                more.add(exit);
                                jMenuBar=new JMenuBar();
                                jMenuBar.add(options);
                                jMenuBar.add(more);
                              //  panel.gameThread.start();
                                setJMenuBar(jMenuBar);
                                add(panel);
                                setTitle("Pong Game");
                                setResizable(false);
                                 setBackground(Color.black);
                                 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                 pack();
                                 setVisible(true);
                                 setLocationRelativeTo(null);
                            }

                        }
