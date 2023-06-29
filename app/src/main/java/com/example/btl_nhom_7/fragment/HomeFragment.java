package com.example.btl_nhom_7.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_nhom_7.Motor.Motor;
import com.example.btl_nhom_7.Motor.MotorAdapter;
import com.example.btl_nhom_7.database.DatabaseHelper;
import com.example.btl_nhom_7.Photo.adapter.PhotoViewPagerAdapter;
import com.example.btl_nhom_7.Photo.model.Photo;
import com.example.btl_nhom_7.R;
import com.example.btl_nhom_7.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<Photo> mListPhoto = new ArrayList<>();

    private RecyclerView rcv;

    ActivityHomeBinding binding;

    private MotorAdapter motorAdapter;

    private List<Motor> motorList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        databaseHelper = new DatabaseHelper(getContext());

//
//        databaseHelper.createMotor(new Motor (0,"SH350i" , 15000000,  "https://cdn.honda.com.vn/motorbike-versions/December2022/gfUfURy1WBwtBbVFH4FO.png","Tại Việt Nam, hình ảnh mẫu xe SH từ lâu đã trở thành biểu tượng cho tính đẳng cấp, sang trọng và sự hoàn hảo. Kế thừa những nét đặc trưng đó, mẫu xe SH350i ra mắt năm 2021 đã gây ấn tượng mạnh mẽ với vẻ đẹp đậm chất hiện đại công nghệ và “bề thế”.","Thông số","Đặc điểm","Kích thước và cân nặng: 2.160 mm x 743 mm x 1.162 mm, 172 kg \nDung tích bình xăng: 11,2 Lít"));
//        databaseHelper.createMotor(new Motor (1,"Rebel 500 2023" , 181000000,  "https://cdn.honda.com.vn/motorbikes/April2023/LgWuSbmoh08VKlrSdvFR.jpg","Tự do tạo dấu ấn của riêng mình Mẫu xe Rebel 500 với khối động cơ 2 xy lanh song song mạnh mẽ, đặt gọn trong bộ khung xe với trọng tâm thấp, phong cách thiết kế tối giản đã đưa chiếc xe trở nên hấp dẫn trong mắt người lái","Thông số","Đặc điểm","Kích thước và cân nặng: 2.206 x 822 x 1.093 mm, 190 kg \nDung tích bình xăng: 11,2 Lít"));
//        databaseHelper.createMotor(new Motor (2,"Future 125 FI" , 31000000,  "https://cdn.honda.com.vn/motorbike-versions/October2021/mTuI6iTbdYCOkHBMnsNw.png","Honda Future 125 FI với thiết kế trẻ trung, lịch lãm và hiện đại được bổ sung màu mới, tạo những điểm nhấn ấn tượng, thu hút mọi ánh nhìn. Cùng với vị thế là mẫu xe số cao cấp hàng đầu phân khúc tại Việt Nam, Future 125 FI cho bạn tự tin thể hiện phong cách, phẩm chất của mình trên mọi hành trình.","Thông số","Đặc điểm","Kích thước và cân nặng: 1.931 mm x 711 mm x 1.083 mm, 104 kg \nDung tích bình xăng: 4,6 Lít"));
//        databaseHelper.createMotor(new Motor (3,"LEAD 125cc" , 41000000,  "https://cdn.honda.com.vn/motorbikes/December2021/PxbOtPG619Vte84CQHPg.png","Kế thừa ngôn ngữ thiết kế hiện đại cùng nhiều tiện ích vượt trội vốn có, xe LEAD 125cc mới nay được nâng tầm với động cơ thế hệ mới nhất của Honda eSP+ như trên các mẫu xe ga cao cấp, màu sắc mới hợp xu hướng, cổng sạc tiện lợi, thiết kế phía trước tinh tế, tem xe nổi bật giúp nâng tầm phong cách và tối đa trải nghiệm lái xe cho người sở hữu.","Thông số","Đặc điểm","Kích thước và cân nặng: 1.844 mm x 680 mm x 1.130 mm, 113 kg \nDung tích bình xăng: 6 Lít"));
//        databaseHelper.createMotor(new Motor (4,"Super Cub C125" , 87800000,  "https://cdn.honda.com.vn/motorbikes/October2021/qdGgJvma2sfdCVaSKhaQ.png","Thiết kế cổ điển, thanh lịch đậm chất Super CUB","Thông số","Đặc điểm","Kích thước và cân nặng: 1.910 x 718 x 1.002mm, 109 kg \nDung tích bình xăng: 3,7 Lít"));
//        databaseHelper.createMotor(new Motor (5,"Air Blade 125/160" , 42790000,  "https://cdn.honda.com.vn/motorbikes/May2022/yXVDCgQDZJcYqcCZPzyQ.png","Xứng danh mẫu xe tay ga thể thao tầm trung hàng đầu trong suốt hơn một thập kỷ qua, AIR BLADE hoàn toàn mới nay được nâng cấp động cơ eSP+ 4 van độc quyền, tiên tiến nhất giúp mang trong mình mãnh lực tiên phong.","Thông số","Đặc điểm","Kích thước và cân nặng: 1.887 x 687 x 1.092 mm, 114 kg \nDung tích bình xăng: 4,4 Lít"));
//        databaseHelper.createMotor(new Motor (6,"CB500F 2022" , 184900000,  "https://cdn.honda.com.vn/motorbike-versions/September2022/A12M6V57z2Hk5f2Ctmbq.jpg","Một dòng xe mang đến sự khác biệt, đánh dấu cá tính đặc trưng của bạn, và cho bạn khám phá những trải nghiệm lái mới: linh hoạt trên đường đô thị mã vẫn mượt mà trên mọi cung đường trường","Thông số","Đặc điểm","Kích thước và cân nặng: 2.080 mm x 800 mm x 1.060 mm, 189 kg \nDung tích bình xăng: 17,1 Lít"));
//        databaseHelper.createMotor(new Motor (7,"Winner X" , 46160000,  "https://cdn.honda.com.vn/motorbike-versions/December2021/AjAslqMuYpko2d6wmuEs.png","WINNER X mới sẵn sàng cùng các tay lái bứt tốc trên mọi hành trình khám phá.","Thông số","Đặc điểm","Kích thước và cân nặng: 2.019 x 727 x 1.104 mm, 120 kg \nDung tích bình xăng: 4,5 Lít"));
//        databaseHelper.createMotor(new Motor (8,"Wave Alpha 110cc" , 18100000,  "https://cdn.honda.com.vn/motorbike-versions/August2022/FGTa8tJ5LqpxS3s7OnZH.png","Wave Alpha phiên bản 2023 trẻ trung và năng động với màu đen mờ hoàn toàn mới cùng thiết kế bộ tem mới ấn tượng, thu hút ánh nhìn, cho bạn tự tin thể hiện cá tính của mình trên mọi hành trình","Thông số","Đặc điểm","Kích thước và cân nặng: 1.914 mm x 688 mm x 1.075 mm, 97 kg \nDung tích bình xăng: 11,2 Lít"));
//        databaseHelper.createMotor(new Motor (1,"Rebel 500 2023" , 181000000,  "https://cdn.honda.com.vn/motorbikes/April2023/LgWuSbmoh08VKlrSdvFR.jpg","Tự do tạo dấu ấn của riêng mình Mẫu xe Rebel 500 với khối động cơ 2 xy lanh song song mạnh mẽ, đặt gọn trong bộ khung xe với trọng tâm thấp, phong cách thiết kế tối giản đã đưa chiếc xe trở nên hấp dẫn trong mắt người lái","Thông số","Đặc điểm","Kích thước và cân nặng: 2.206 x 822 x 1.093 mm, 190 kg \nDung tích bình xăng: 3,7 Lít"));
//        databaseHelper.createMotor(new Motor (1,"Rebel 500 2023" , 181000000,  "https://cdn.honda.com.vn/motorbikes/April2023/LgWuSbmoh08VKlrSdvFR.jpg","Tự do tạo dấu ấn của riêng mình Mẫu xe Rebel 500 với khối động cơ 2 xy lanh song song mạnh mẽ, đặt gọn trong bộ khung xe với trọng tâm thấp, phong cách thiết kế tối giản đã đưa chiếc xe trở nên hấp dẫn trong mắt người lái","Thông số","Đặc điểm","Kích thước và cân nặng: 2.206 x 822 x 1.093 mm, 190 kg \nDung tích bình xăng: 11,2 Lít"));
//        databaseHelper.createMotor(new Motor (1,"Rebel 500 2023" , 181000000,  "https://cdn.honda.com.vn/motorbikes/April2023/LgWuSbmoh08VKlrSdvFR.jpg","Tự do tạo dấu ấn của riêng mình Mẫu xe Rebel 500 với khối động cơ 2 xy lanh song song mạnh mẽ, đặt gọn trong bộ khung xe với trọng tâm thấp, phong cách thiết kế tối giản đã đưa chiếc xe trở nên hấp dẫn trong mắt người lái","Thông số","Đặc điểm","Kích thước và cân nặng: 2.206 x 822 x 1.093 mm, 190 kg \nDung tích bình xăng: 11,2 Lít"));
//

//        databaseHelper.createMotor(new Motor ("Yamaha" , 15000 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png"));
//        databaseHelper.createMotor(new Motor ("Suzuki" , 1600 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png"));
//        databaseHelper.createMotor(new Motor ("Kawasaki" , 17000 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png"));

      //databaseHelper.clearMotorTable();

        motorList = databaseHelper.getAllMotor();

//        motorList.add(new Motor ("Honda" , 1000 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Yamaha" , 1500 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Suzuki" , 1200 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;
//        motorList.add(new Motor ("Kawasaki" , 1800 ,  "https://cdn.honda.com.vn/motorbike-versions/April2023/2pSqu65qdei9HLkHfOtJ.png")) ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = view.findViewById(R.id.view_page);
        mCircleIndicator = view.findViewById(R.id.circle_indicator);
        rcv = view.findViewById(R.id.list_motor);
        mListPhoto = getListPhoto();

        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(mListPhoto);
        mViewPager.setAdapter(adapter);
        mCircleIndicator.setViewPager(mViewPager);

        // RCV Adapter

        motorAdapter = new MotorAdapter(getContext(),motorList);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false );
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(motorAdapter);

        return view;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bgimg_1));
        list.add(new Photo(R.drawable.bgimg_2));
        list.add(new Photo(R.drawable.bgimg_3));
        list.add(new Photo(R.drawable.bgimg_4));

        return list;
    }



}