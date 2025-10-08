package secondyear;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageConverter extends JFrame {
    private JLabel label;
    private JButton button;

    public ImageConverter() {
        super("Color to Black & White Converter");
        setLayout(new FlowLayout());

        label = new JLabel("Choose an image to convert");
        button = new JButton("Upload Image");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(file);
                        if (isGrayscale(img)) {
                            JOptionPane.showMessageDialog(null, "This image is already black & white!");
                        } else {
                            BufferedImage gray = toGrayScale(img);
                            File output = new File("converted_image.png");
                            ImageIO.write(gray, "png", output);
                            JOptionPane.showMessageDialog(null, "Image converted to black & white!\nSaved as converted_image.png");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        add(label);
        add(button);

        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Convert colored image to grayscale
    private BufferedImage toGrayScale(BufferedImage img) {
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = gray.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return gray;
    }

    // Check if image is already grayscale
    private boolean isGrayscale(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                if (r != g || g != b) {
                    return false; // Found a colored pixel
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new ImageConverter();
    }
}
