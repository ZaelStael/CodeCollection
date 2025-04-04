package objectAdventure.common;
// $Id: Utils.java 720 2024-11-18 20:54:59Z tbaker17 $

import objectAdventure.core.DescriptionType;
import objectAdventure.core.item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * General static utility methods.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class Utils {

    /**
     * Maximum size of the image.
     */
    private static final int MAX_IMAGE_SIZE = 500;

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
        // Prevent instantiation, this is a utility class.
    }

    /**
     * Capitalize the first letter of the string.
     *
     * @param str The string to capitalize
     * @return The capitalized string
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            if (str.length() == 1) {
                return str.toUpperCase();
            } else {
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        }
    }

    /**
     * Get a list of items in a list display format.
     *
     * @param itemCollection  The collection of items
     * @param descriptionType Either DescriptionType.SHORT or DescriptionType.LONG
     * @return The list of items in a string format
     */
    public static String getFormattedItemList(
            Collection<? extends Item> itemCollection, DescriptionType descriptionType) {

        return itemCollection
                .stream()
                .map(switch (descriptionType) {
                    case SHORT -> Item::getItemDisplayName;
                    case LONG -> Item::getItemFullDescription;
                })
                .map(Utils::capitalize).map("*  "::concat).collect(Collectors.joining("\n"));
    }

    /**
     * Concatenate a list of lists into a single list.
     *
     * @param lists The lists to concatenate
     * @param <T>   The type of the list
     * @return The concatenated list
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> concatLists(List<? extends T>... lists) {
        return Stream.of(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Wrap input text to a specified width.
     * <p>
     * Note: Several styles of word wrapping algorithms exist, mainly "early wrapping or late
     * wrapping". This is a late wrapping algorithm, which means that the text does not wrap until
     * the word is longer than the width. In early wrapping algorithms, the text is wrapped as soon
     * as the *next* word would exceed the specified width. "Late wrapping" is simpler to implement,
     * but doesn't work on displays with a fixed number of character columns.
     * <p>
     * NOTE: Algorithms, which attempt to keep the text as "fully justified" as possible exist, but
     * those are complex. I actually had to write code to do this in <a
     * href="https://en.wikipedia.org/wiki/Zilog_Z80">Z80</a> assembly in the late 1990s… fun times!
     * (If you google for "TI-73 math mastery modules", you can probably find screenshots from that
     * project.)
     *
     * @param text   The text to wrap
     * @param width  The width of the wrapped text.
     * @param reflow If true, strip existing newlines and extra spaces.
     * @return The wrapped text.
     */
    public static String wrapText(String text, final int width, final boolean reflow) {
        // Get rid of new-lines and extra spaces.
        if (reflow) {
            text = text.replace("\n", " ").replaceAll("\\s+", " ");
        }

        // String builder to hold the wrapped text.
        StringBuilder sb = new StringBuilder();

        // Loop through the text, adding a new-line when the width is reached.
        // Yes, I'm pretending to be a C programmer here. :)
        for (int i = 0, col = 0; i < text.length(); i++, col++) {
            char theChar = text.charAt(i);

            // If we've reached the width (or we have a new line), add a new-line.
            if (col >= width && theChar == ' ' || theChar == '\n') {
                sb.append('\n');
                col = 0;
                continue;
            }

            // If we're at the beginning of a line, and we have a space, skip it.
            if (col == 0 && theChar == ' ') {
                continue;
            }

            // Add the character to the string builder.
            sb.append(theChar);
        }

        // Return the wrapped text.
        return sb.toString();
    }

    /**
     * Wrap text to a default width of 80 characters.
     *
     * @param text The text to wrap.
     * @return The wrapped text.
     * @see #wrapText(String, int, boolean)
     */
    public static String wrapText(String text) {
        return wrapText(text, 80, true);
    }


    /**
     * Display an image in a pop-up window.
     *
     * @param imagePath The path to the image resource.
     * @param title     The title of the pop-up window.
     */
    public static void displayImagePopup(String title, String imagePath) {
        // Load the image from the resource.
        try (InputStream imageStream = Utils.class.getResourceAsStream(imagePath)) {

            // Check if the image resource was found.
            if (imageStream == null) {
                System.err.println("Image resource not found: " + imagePath);
                return;
            }

            // Create a new JFrame.
            final JFrame frame = new JFrame(title);

            // Read the image.
            final Image image = ImageIO.read(imageStream);
            final JLabel imageLabel = new JLabel(new ImageIcon(image));

            // Set the default close operation.
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Set the background colors
            frame.setBackground(Color.BLACK);
            imageLabel.setBackground(Color.BLACK);

            // Add the image label to the frame.
            frame.getContentPane().add(imageLabel);
            SwingUtilities.invokeLater(() -> {
                // Add a component listener to handle resizing.
                frame.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        // Get the new size of the frame.
                        int width = frame.getWidth();
                        int height = frame.getHeight();

                        // Scale the image to the new size.
                        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(scaledImage));
                    }
                });

                displayFrame(frame, image, MAX_IMAGE_SIZE);
            });

        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
        }
    }

    /**
     * Set the initial size of the frame based on the image size.
     *
     * @param frame          The frame to set the size of.
     * @param image          The image to display.
     * @param MAX_IMAGE_SIZE The maximum size of the image.
     */
    private static void displayFrame(JFrame frame, Image image, int MAX_IMAGE_SIZE) {
        // Get the width and height of the image.
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        // Calculate the aspect ratio of the image.
        float aspectRatio = (float) imgWidth / imgHeight;

        // Determine the frame width and height, not exceeding 500x500 while preserving the aspect ratio.
        int frameWidth = Math.min(MAX_IMAGE_SIZE, imgWidth);
        int frameHeight = Math.min(MAX_IMAGE_SIZE, imgHeight);

        // Adjust the frame dimensions to maintain the aspect ratio.
        if (frameWidth / aspectRatio <= MAX_IMAGE_SIZE) {
            frameHeight = (int) (frameWidth / aspectRatio);
        } else {
            frameWidth = (int) (frameHeight * aspectRatio);
        }

        // Set the size of the frame.
        frame.setSize(frameWidth, frameHeight);

        // Center the frame on the screen.
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);

        // Make the frame visible.
        frame.setVisible(true);

        // Set the frame to be focusable and request focus.
        frame.setAlwaysOnTop(true);
        frame.requestFocus();
    }
}
