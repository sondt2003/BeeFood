package android.BeeFood.master.view.add_san_pham;

import static java.lang.Thread.sleep;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.FoodDao;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fragment_add_san_pham extends Fragment implements LocationListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();

    ImageView img_addSanPham_avtFood;
    EditText edt_addSanPham_nameFood, edt_addSanPham_Price, edt_addSanPham_Address, edt_addSanPham_PhoneNumber, edt_addSanPham_moTa;
    Spinner spn_addSanPham_IDLoai;
    Button btn_addSanPham_add;
    ImageView addSanPham_avtFood;
    double latitude = 0, longitude = 0;
    LocationManager locationManager;
    Uri uri;
    String url_profile;
    String address;
    public Fragment_add_san_pham() {
        // Required empty public constructor
    }


    public static Fragment_add_san_pham newInstance() {
        Fragment_add_san_pham fragment = new Fragment_add_san_pham();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
//        upLoadURL(view);
        setSelect_edt(edt_addSanPham_nameFood);
        setSelect_edt(edt_addSanPham_Price);
        setSelect_edt(edt_addSanPham_Address);
        setSelect_edt(edt_addSanPham_PhoneNumber);
        setSelect_edt(edt_addSanPham_moTa);

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Chọn thể loại");
        spinnerArray.add("1");
        spinnerArray.add("2");
        spinnerArray.add("3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_addSanPham_IDLoai.setAdapter(adapter);
        img_addSanPham_avtFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 174);
            }
        });
        evenCLick();

        TextInputLayout textInputLayout  = view.findViewById(R.id.layout_addSanPham_Address);
        textInputLayout.getStartIconDrawable();

        getLocation();
    }

//    private void upLoadURL(View view) {
//        db.collection("food")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                DataFirestore dataFirestore=new DataFirestore();
//                                try {
//                                    if (dataFirestore.getEmail(getActivity()).equalsIgnoreCase(document.getData().get("email").toString())) {
//                                        url_profile = document.getData().get("ImageUrl").toString();
//                                        edt_addSanPham_nameFood.setText(document.getData().get("namefood").toString());
//                                        edt_addSanPham_Price.setText(document.getData().get("price").toString());
//                                        edt_addSanPham_Address.setText(document.getData().get("address").toString());
//                                        edt_addSanPham_PhoneNumber.setText(document.getData().get("phonenumber").toString());
//                                        if(document.getData().get("idloaifood").equals("0")){
//                                            spn_addSanPham_IDLoai.setSelection(0);
//                                        } else {
//                                            spn_addSanPham_IDLoai.setSelection(1);
//                                        }
//                                        Glide.with(getActivity()).load(url_profile).into(img_addSanPham_avtFood);
//                                    }
//                                } catch (Exception e) {
//                                }
//                            }
//                        }
//                    }
//                });
//    }
    private void evenCLick() {
        btn_addSanPham_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_addSanPham_nameFood.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Chưa Nhập  Name Food", Toast.LENGTH_SHORT).show();
                } else if (edt_addSanPham_Price.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Chưa Nhập Giá Tiền", Toast.LENGTH_SHORT).show();
                } else if (edt_addSanPham_Address.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Chưa Nhập Địa Chỉ", Toast.LENGTH_SHORT).show();
                } else if (edt_addSanPham_PhoneNumber.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Chưa Nhập SDT Liên hệ", Toast.LENGTH_SHORT).show();
                } else if (spn_addSanPham_IDLoai.getSelectedItemPosition() == 0) {
                    Toast.makeText(getActivity(), "Chưa Chọn Thể Loại", Toast.LENGTH_SHORT).show();
                } else {
                    if (uri != null) {
                        FoodDao foodDao=new FoodDao();

                        StorageReference demoRef = reference.child(foodDao.getEmail(getActivity())+"food.png");
                        demoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                                demoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri DownloadUri) {
                                        url_profile = DownloadUri.toString();
                                        Log.i("SONDTPH",url_profile);
                                        Toast.makeText(getActivity(), "CHECK:"+url_profile, Toast.LENGTH_SHORT).show();
                                        Food food=new Food(edt_addSanPham_nameFood.getText().toString(),
                                                edt_addSanPham_Price.getText().toString(),
                                                edt_addSanPham_Address.getText().toString(),
                                                edt_addSanPham_PhoneNumber.getText().toString(),
                                                foodDao.getEmail(getActivity()),
                                                spn_addSanPham_IDLoai.getSelectedItem().toString(),
                                                url_profile,
                                                edt_addSanPham_moTa.getText().toString()
                                                );
                                        boolean check =foodDao.AddDataFood(food,getActivity());
                                        Toast.makeText(getActivity(), check+"", Toast.LENGTH_SHORT).show();
                                        if(check){
                                            startActivity(new Intent(getActivity(), HomeActivity.class));
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Vui lòng Chọn Ảnh Sản Phẩm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void anhXa(View v) {
        img_addSanPham_avtFood = v.findViewById(R.id.addSanPham_avtFood);
        edt_addSanPham_nameFood = v.findViewById(R.id.addSanPham_nameFood);
        edt_addSanPham_Price = v.findViewById(R.id.addSanPham_Price);
        edt_addSanPham_Address = v.findViewById(R.id.addSanPham_Address);
        edt_addSanPham_PhoneNumber = v.findViewById(R.id.addSanPham_PhoneNumber);
        edt_addSanPham_moTa = v.findViewById(R.id.addSanPham_moTa);
        spn_addSanPham_IDLoai = v.findViewById(R.id.addSanPham_IDLoai);
        btn_addSanPham_add = v.findViewById(R.id.addSanPham_add);
    }

    public void setSelect_edt(EditText edt) {
        edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    edt.setBackgroundResource(R.drawable.bg_edt_login_select);
                } else {
                    edt.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 174) {
            uri = data.getData();
            if (uri != null) {
                Glide.with(getActivity()).load(uri).into(img_addSanPham_avtFood);
            }
        }
    }

    private void getLocation() {
//        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //goi ham update
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Toast.makeText(getActivity(), "Đang lấy vị trí", Toast.LENGTH_SHORT).show();
                    if (longitude == 0 && latitude == 0) {
                        try {
                            edt_addSanPham_Address.setText(address);
                            Toast.makeText(getActivity(), "lấy vị trí thành công", Toast.LENGTH_SHORT).show();
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}