package android.BeeFood.master.View.Food_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.BeeFood.master.R;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_To_Basket extends AppCompatActivity {
    private Toolbar toolbar_add_To_Basket_toolbar;
    private ImageView img_add_To_Basket_banner;
    private TextView tv_add_To_Basket_name,tv_add_To_Basket_description,tv_add_To_Basket_number;
    private ImageButton btn_add_To_Basket_number_giam,btn_add_To_Basket_number_tang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_basket);

        setSupportActionBar(toolbar_add_To_Basket_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anhXa();
    }

    public void anhXa() {
        toolbar_add_To_Basket_toolbar = findViewById(R.id.add_To_Basket_toolbar);

        img_add_To_Basket_banner = findViewById(R.id.add_To_Basket_banner);
        tv_add_To_Basket_name = findViewById(R.id.add_To_Basket_name);
        tv_add_To_Basket_description = findViewById(R.id.add_To_Basket_description);
        tv_add_To_Basket_number = findViewById(R.id.add_To_Basket_number);

        btn_add_To_Basket_number_giam = findViewById(R.id.add_To_Basket_number_giam);
        btn_add_To_Basket_number_tang = findViewById(R.id.add_To_Basket_number_tang);
    }
}