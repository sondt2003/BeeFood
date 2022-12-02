package android.BeeFood.master.view.add_san_pham;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Fragment_add_san_pham extends Fragment {

    ImageView img_addSanPham_avtFood;
    EditText edt_addSanPham_nameFood,edt_addSanPham_Price,edt_addSanPham_Address,edt_addSanPham_PhoneNumber,edt_addSanPham_moTa;
    Spinner spn_addSanPham_IDLoai;
    Button btn_addSanPham_add;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa(view);
        //hiệp óc chó

        setSelect_edt(edt_addSanPham_nameFood);
        setSelect_edt(edt_addSanPham_Price);
        setSelect_edt(edt_addSanPham_Address);
        setSelect_edt(edt_addSanPham_PhoneNumber);
        setSelect_edt(edt_addSanPham_moTa);

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("1");
        spinnerArray.add("2");
        spinnerArray.add("3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_addSanPham_IDLoai.setAdapter(adapter);
    }

    public void anhXa(View v){
        img_addSanPham_avtFood = v.findViewById(R.id.addSanPham_avtFood);
        edt_addSanPham_nameFood = v.findViewById(R.id.addSanPham_nameFood);
        edt_addSanPham_Price = v.findViewById(R.id.addSanPham_Price);
        edt_addSanPham_Address = v.findViewById(R.id.addSanPham_Address);
        edt_addSanPham_PhoneNumber = v.findViewById(R.id.addSanPham_PhoneNumber);
        edt_addSanPham_moTa = v.findViewById(R.id.addSanPham_moTa);
        spn_addSanPham_IDLoai = v.findViewById(R.id.addSanPham_IDLoai);
        btn_addSanPham_add = v.findViewById(R.id.addSanPham_add);
    }

    public void setSelect_edt(EditText edt){

        edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

    }
}