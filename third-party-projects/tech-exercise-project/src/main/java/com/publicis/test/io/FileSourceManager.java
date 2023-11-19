package com.publicis.test.io;

import com.publicis.test.exception.FileSourceException;
import com.publicis.test.model.Coordinate;
import com.publicis.test.model.Direction;
import com.publicis.test.model.Order;
import com.publicis.test.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSourceManager {

    private Coordinate borderCoordinate;
    private final List<Position> positionList;
    private final List<List<Order>> orderList;

    private static final String LINE_SPACE = " ";
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSourceManager.class);

    public FileSourceManager(String filepath) throws FileSourceException {
        this.positionList = new ArrayList<>();
        this.orderList = new ArrayList<>();
        loadFile(filepath);
    }

    public void loadFile(String filepath) throws FileSourceException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            readBorderCoordinate(reader.readLine());
            while (true) {
                String initPositionLine = reader.readLine();
                if (initPositionLine == null) {
                    break;
                }
                String ordersLine = reader.readLine();
                readInitPositionAndOrders(initPositionLine, ordersLine);
            }
        } catch (IOException exception) {
            throw new FileSourceException(exception.getMessage());
        }
    }

    private void readBorderCoordinate(String firstLine) throws FileSourceException {
        LOGGER.info("Read file line '{}' for border coordinate", firstLine);
        if (!LineValidator.isValidBorderCoordinate(firstLine)) {
            throw new FileSourceException("The Border coordinate is not valid");
        }
        String[] strings = firstLine.split(LINE_SPACE);
        int coordinateX = Integer.parseInt(strings[0]);
        int coordinateY = Integer.parseInt(strings[1]);
        this.borderCoordinate = new Coordinate(coordinateX, coordinateY);
    }

    private void readInitPositionAndOrders(String initPositionLine, String ordersLine) throws FileSourceException {
        if (!LineValidator.isValidInitPosition(initPositionLine)) {
            throw new FileSourceException("The format of init position is not correct");
        }
        if (!LineValidator.isValidOrders(ordersLine)) {
            throw new FileSourceException("Wrong order instruction");
        }
        this.positionList.add(getPosition(initPositionLine));
        this.orderList.add(getOrders(ordersLine));
    }

    private Position getPosition(String initPositionLine) {
        LOGGER.info("Read init position '{}'", initPositionLine);
        String[] strings = initPositionLine.split(LINE_SPACE);
        int coordinateX = Integer.parseInt(strings[0]);
        int coordinateY = Integer.parseInt(strings[1]);
        Direction direction = getDirection(strings[2]);

        Coordinate coordinate = new Coordinate(coordinateX, coordinateY);
        return new Position(coordinate, direction);
    }

    private List<Order> getOrders(String ordersLine) {
        LOGGER.info("Read orders line string '{}'", ordersLine);
        List<Order> orders = new ArrayList<>();
        for (char c: ordersLine.toCharArray()) {
            if (c == 'G') {
               orders.add(Order.G);
            } else if (c == 'D') {
                orders.add(Order.D);
            } else {
                orders.add(Order.A);
            }
        }
        return orders;
    }

    private Direction getDirection(String value) {
        switch (value) {
            case "N":
                return Direction.N;
            case "E":
                return Direction.E;
            case "W":
                return Direction.W;
            default:
                return Direction.S;
        }
    }

    public Coordinate getBorderCoordinate() {
        return this.borderCoordinate;
    }

    public int countMachineDeployed() {
        return this.positionList.size();
    }

    public Position getPositionByIndex(int index) {
        return this.positionList.get(index);
    }

    public List<Order> getOrderListByIndex(int index) {
        return this.orderList.get(index);
    }
}
