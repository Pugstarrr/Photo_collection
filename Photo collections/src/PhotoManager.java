
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PhotoManager extends JFrame {

    private static final long serialVersionUID = 1L;

    private List<BufferedImage> photos;
    private int currentPhotoIndex;

    private JLabel photoLabel;
    private JTextField albumNameTextField;
    private JButton importButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton editButton;
    private JButton shareButton;

    public PhotoManager() {
        super("Фотоколлекция");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        photos = new ArrayList<>();
        currentPhotoIndex = 0;

        photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(640, 480));

        albumNameTextField = new JTextField();
        albumNameTextField.setPreferredSize(new Dimension(100, 25));

            importButton = new JButton("Импорт");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importPhotos();
            }
        });

        previousButton = new JButton("Предыдущий");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPhoto();
            }
        });

        nextButton = new JButton("Следующий");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPhoto();
            }
        });

        editButton = new JButton("Редактировать");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPhoto();
            }
        });

        shareButton = new JButton("Делиться");
        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sharePhoto();
            }
        });

        JToolBar toolbar = new JToolBar();
        toolbar.add(importButton);
        toolbar.add(previousButton);
        toolbar.add(nextButton);
        toolbar.add(editButton);
        toolbar.add(shareButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(photoLabel, BorderLayout.CENTER);
        panel.add(albumNameTextField, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }

    private void importPhotos() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "bmp", "gif"));
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            for (File file : fileChooser.getSelectedFiles()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    photos.add(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            displayPhoto(currentPhotoIndex);
        }
    }

    private void previousPhoto() {
        if (currentPhotoIndex > 0) {
            currentPhotoIndex--;
            displayPhoto(currentPhotoIndex);
        }
    }

    private void nextPhoto() {
        if (currentPhotoIndex < photos.size() - 1) {
            currentPhotoIndex++;
            displayPhoto(currentPhotoIndex);
        }
    }

    private void editPhoto() {
        // TODO: Implement photo editing functionality
    }

    private void sharePhoto() {
        // TODO: Implement photo sharing functionality
    }

    private void displayPhoto(int index) {
        photoLabel.setIcon(new ImageIcon(photos.get(index)));
    }

    public static void main(String[] args) {
        new PhotoManager();
    }
}
