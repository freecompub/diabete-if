package ifdiabetes.freecompub.com.diabetesinsulintherapy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ifdiabetes.freecompub.com.diabetesinsulintherapy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingActivityFragment extends Fragment {

    public SettingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}
