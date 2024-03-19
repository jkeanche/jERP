package component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import models.Reservation;

public class HotelPlanner extends JPanel {
    private final static int DAYS_IN_WEEK = 7;

    private final List<Reservation> reservations;
    private final JToolBar toolbar;
    private final JButton monthlyButton;
    private final JButton weeklyButton;
    private final JLabel monthYearLabel;

    private boolean isMonthlyMode = true;
    
    private boolean monthlyMode;
    private LocalDate selectedDate;

    public HotelPlanner() {
        setLayout(new BorderLayout());
        monthlyMode = true;
        selectedDate = LocalDate.now().withDayOfMonth(1);
        // Create toolbar
        toolbar = new JToolBar();
        monthYearLabel = new JLabel();
        monthlyButton = new JButton("Monthly Mode");
        weeklyButton = new JButton("Weekly Mode");

        toolbar.add(monthYearLabel);
        toolbar.add(monthlyButton);
        toolbar.add(weeklyButton);
//
        monthlyButton.addActionListener(e -> {
            isMonthlyMode = true;
            updateToolbar();
            renderReservations();
        });

        weeklyButton.addActionListener(e -> {
            isMonthlyMode = false;
            updateToolbar();
            renderReservations();
        });

        add(toolbar, BorderLayout.NORTH);

        // Create sample reservations
        reservations = new ArrayList<>();
        reservations.add(new Reservation("Room 1", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 3), "John Doe"));
        reservations.add(new Reservation("Room 2", LocalDate.of(2024, 3, 2), LocalDate.of(2024, 3, 5), "Jane Smith"));

        // Initialize with current month
//        updateToolbar();
        renderReservations();
    }

    private void updateToolbar() {
        LocalDate currentDate = LocalDate.now();
        if (isMonthlyMode) {
            monthYearLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)));
        } else {
            LocalDate startDateOfWeek = currentDate.with(java.time.DayOfWeek.MONDAY);
            LocalDate endDateOfWeek = startDateOfWeek.plusDays(DAYS_IN_WEEK - 1);
            monthYearLabel.setText(startDateOfWeek.format(DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH))
                    + " - " +
                    endDateOfWeek.format(DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH)));
        }
    }

    private void renderReservations() {
        //removeAll();

        // Create grid layout
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new MigLayout("", "[100px,fill][grow]", "[100px,fill]")); // Allow last day to wrap

        // Add days of the month or week to grid
        LocalDate currentDate = LocalDate.now();
        if (isMonthlyMode) {
            LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
            for (int i = 0; i < firstDayOfMonth.lengthOfMonth(); i++) {
                JLabel dayLabel = new JLabel(firstDayOfMonth.plusDays(i).format(DateTimeFormatter.ofPattern("MMM d")));
                
                gridPanel.add(dayLabel, "grow");
            }
        } else {
            LocalDate startDateOfWeek = currentDate.with(java.time.DayOfWeek.MONDAY);
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                JLabel dayLabel = new JLabel(startDateOfWeek.plusDays(i).format(DateTimeFormatter.ofPattern("MMM d")));
                gridPanel.add(dayLabel, "grow");
            }
        }

        // Add reservations to grid
        for (Reservation reservation : reservations) {
            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = reservation.getEndDate();

            int startColumn = startDate.getDayOfMonth();
            int endColumn = endDate.getDayOfMonth();

            JPanel reservationPanel = new JPanel();
            reservationPanel.setLayout(new BorderLayout());
            reservationPanel.setBorder(new LineBorder(Color.BLACK, 1));

            JLabel nameLabel = new JLabel(reservation.getClient());
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            reservationPanel.add(nameLabel, BorderLayout.CENTER);

            // Set background color
            boolean yellowBackground = true;
            for (int day = startColumn; day <= endColumn; day++) {
                if (!yellowBackground) {
                    reservationPanel.setBackground(Color.GRAY);
                } else {
                    reservationPanel.setBackground(new Color(18, 99, 63));
                }
                yellowBackground = !yellowBackground;
                if (day >= startColumn && day <= endColumn) {
                              JPanel dayPanel = new JPanel(new BorderLayout());
                              dayPanel.add(reservationPanel, BorderLayout.CENTER);
                              gridPanel.add(dayPanel, "cell " + day + " 0, grow"); // Corrected constraint for reservationPanel
                              dayPanel.setToolTipText((day >= startColumn && day <= endColumn) ?
                                      reservation.getClient() + " : " +reservation.getStartDate()+" to "+reservation.getEndDate()
                                              + " -"+ reservation.getRoom():
                                      "Not reserved");
                          }
                JPanel dayPanel = new JPanel(new BorderLayout());
                dayPanel.add(reservationPanel, BorderLayout.CENTER);
                gridPanel.add(dayPanel, "cell " + (day - 1) + " 1, grow"); // Skip the first row for day labels
            }

            // Span reservation across days
            int span = endColumn - startColumn + 1;
            setComponentSize(reservationPanel, span);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

//        JPanel toolbarPanel = createToolbar();
//        add(toolbarPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }
    
    private JPanel createToolbar() {
        JPanel toolbarPanel = new JPanel(new MigLayout());
        JButton monthlyButton = new JButton("Monthly Mode");
        JButton weeklyButton = new JButton("Weekly Mode");
        JLabel dateLabel = new JLabel(selectedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        toolbarPanel.add(monthlyButton, "split");
        toolbarPanel.add(weeklyButton);
        toolbarPanel.add(dateLabel, "wrap");

        monthlyButton.addActionListener(e -> {
            monthlyMode = true;
            dateLabel.setText(selectedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
            renderReservations();
        });

        weeklyButton.addActionListener(e -> {
            monthlyMode = false;
            dateLabel.setText(selectedDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            renderReservations();
        });

        return toolbarPanel;
    }

    private void setComponentSize(Component component, int span) {
        Dimension dimension = new Dimension(span * 100, 100);
        component.setPreferredSize(dimension);
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
    }



    // Main method to test the component
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hotel Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new HotelPlanner());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
