package ru.dekar.qr4all.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.dekar.qr4all.models.ItemEntity;

public class ItemMocks {

    private static String[][] data = {
            {"1", "Favorite books", "My favorite books are in this box", "https://images.pexels.com/photos/159866/books-book-pages-read-literature-159866.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/440px-QR_code_for_mobile_English_Wikipedia.svg.png", "-34.92", "138.58"},
            {"12", "Favorite toys", "My favorite toys are in this box", "http://www.toylandcompany.com/images/product/toys-pic-2.gif", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/440px-QR_code_for_mobile_English_Wikipedia.svg.png", "53.95", "-1.06"},
            {"10", "Cat's stuffs", "MCat's stuffs are in this box", "https://i.etsystatic.com/13177485/r/il/42face/1060638866/il_570xN.1060638866_iwlv.jpg", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/440px-QR_code_for_mobile_English_Wikipedia.svg.png","45.40", "141.67"},
            {"2", "Furniture", "Furniture are in this box", "https://i5.walmartimages.com/asr/67861393-915b-45ea-89ff-4a19c64f7b10_1.7eae27c1599484894891a627255c0278.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/440px-QR_code_for_mobile_English_Wikipedia.svg.png", "34.92", "138.58"},
            {"20", "Exercise books", "Ex are in this box", "http://www.oakheights.co.uk/wp-content/uploads/2016/09/ex-bundle.jpg", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/QR_code_for_mobile_English_Wikipedia.svg/440px-QR_code_for_mobile_English_Wikipedia.svg.png", "59.9342802","30.3350986"},
    };

    public static List<ItemEntity> getMocks() {

        List<ItemEntity> result = new ArrayList<ItemEntity>();

        for (int i = 0; i < data.length; i++) {
            result.add(
                    new ItemEntity(data[i][1], data[i][2], data[i][3], data[i][4], data[i][5], data[i][6], new Date())
            );

        }

        return result;
    }

}
