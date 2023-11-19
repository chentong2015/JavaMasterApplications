package com.publicis.test.io;

import com.publicis.test.exception.FileSourceException;
import com.publicis.test.helper.TestResourceHelper;
import com.publicis.test.model.Coordinate;
import com.publicis.test.model.Direction;
import com.publicis.test.model.Order;
import com.publicis.test.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class FileSourceManagerTest {

    @Test
    void testLoadFileSimpleCase() throws FileSourceException {
        String fullPath = TestResourceHelper.getFullPath("file_simple_case.txt");
        FileSourceManager fileSourceManager = new FileSourceManager(fullPath);

        Coordinate borderCoordinate = fileSourceManager.getBorderCoordinate();
        Assertions.assertEquals(5, borderCoordinate.getX());
        Assertions.assertEquals(5, borderCoordinate.getY());

        Position position = fileSourceManager.getPositionByIndex(0);
        Assertions.assertEquals(1, position.getCoordinate().getX());
        Assertions.assertEquals(2, position.getCoordinate().getY());
        Assertions.assertEquals(Direction.N, position.getDirection());
        List<Order> orderList = fileSourceManager.getOrderListByIndex(0);
        Assertions.assertEquals("[G, A, G, A, G, A, G, A, A]", orderList.toString());

        position = fileSourceManager.getPositionByIndex(1);
        Assertions.assertEquals(3, position.getCoordinate().getX());
        Assertions.assertEquals(3, position.getCoordinate().getY());
        Assertions.assertEquals(Direction.E, position.getDirection());
        orderList = fileSourceManager.getOrderListByIndex(1);
        Assertions.assertEquals("[A, A, D, A, A, D, A, D, D, A]", orderList.toString());
    }
}
