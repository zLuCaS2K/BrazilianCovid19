package com.lucasprojects.braziliancovid19.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.button.MaterialButton
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.lucasprojects.braziliancovid19.R
import com.lucasprojects.braziliancovid19.databinding.FragmentHeatMapBinding
import com.lucasprojects.braziliancovid19.model.domain.data.Data
import com.lucasprojects.braziliancovid19.ui.viewmodel.MainViewModel
import com.lucasprojects.braziliancovid19.utils.Utils
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*

class HeatMapFragment : Fragment() {

    private var _binding: FragmentHeatMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewRoot: View
    private lateinit var mMap : GoogleMap
    private lateinit var mOverlay: TileOverlay
    private lateinit var mDialogLoading: Dialog
    private lateinit var mDialogAnError: Dialog
    private val mMainViewModel: MainViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(activity, R.raw.maps_styles))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-14.235004, -51.925282)))
        mMap.setMinZoomPreference(3.9f)
        mMap.setMaxZoomPreference(3.9f)
        mMainViewModel.loadAllDataCity()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHeatMapBinding.inflate(inflater, container, false)
        mViewRoot = binding.root
        initializeAlertDialogs()
        return mViewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapHeatMapCity) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        setupObservers()
        binding.fabRefreshDataMap.setOnClickListener {
            removeHeatMap()
            mMainViewModel.loadAllDataCity()
        }
    }

    private fun setupObservers() {
        mMainViewModel.mListDataCity.observe(viewLifecycleOwner, {
            addHeatMap(it)
        })
        mMainViewModel.mIsViewLoading.observe(viewLifecycleOwner, {
            if (it) mDialogLoading.show() else mDialogLoading.dismiss()
        })
        mMainViewModel.mAnErrorOccurred.observe(viewLifecycleOwner, {
            if (it) {
                mDialogAnError.show()
                mDialogAnError.findViewById<MaterialButton>(R.id.btnTryAgain).setOnClickListener {
                    mDialogAnError.dismiss()
                    mMainViewModel.loadAllData(mViewRoot.context)
                }
            } else {
                mDialogAnError.dismiss()
            }
        })
    }

    private fun initializeAlertDialogs() {
        mDialogLoading = Utils.showAlertDialog(mViewRoot.context, 1)
        mDialogAnError = Utils.showAlertDialog(mViewRoot.context, 2)
    }

    private fun addHeatMap(listStates: List<Data>) {
        var listWeiLng : List<WeightedLatLng>? = null
        val jsonArray = readJsonFileCity()
        val city = readCity(jsonArray)

        try {
            listWeiLng = readItems(listStates, city)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val colors = intArrayOf(
            Color.rgb(255,178,89),
            Color.rgb(255,178,20)
        )
        val startPoints = floatArrayOf(0.2f, 1f)
        val gradient = Gradient(colors, startPoints)
        mOverlay = mMap.addTileOverlay(
            TileOverlayOptions().tileProvider(
                HeatmapTileProvider.Builder()
                    .weightedData(listWeiLng)
                    .radius(30)
                    .gradient(gradient)
                    .build()
            )
        )
    }

    private fun removeHeatMap() {
        mOverlay.remove()
    }

    private fun readJsonFileCity() : JSONArray {
        val inputStream : InputStream = resources.openRawResource(R.raw.municipios)
        val json : String = Scanner(inputStream).useDelimiter("//A").next()
        return JSONArray(json)
    }

    @Throws(JSONException::class)
    private fun readCity(jsonArray: JSONArray) : ArrayList<Triple<Double, Double, String>> {
        val list = arrayListOf<Triple<Double, Double, String>>()
        val size = jsonArray.length()

        for (i in 0 until size) {
            val ob = jsonArray.getJSONObject(i)
            val city = ob.getString("nome")
            val lat = ob.getDouble("latitude")
            val log = ob.getDouble("longitude")
            list.add(Triple(lat, log, city))
        }
        return list
    }

    @Throws(JSONException::class)
    private fun readItems(listStates: List<Data>, city : List<Triple<Double, Double, String>>) : ArrayList<WeightedLatLng> {
        val listWeiLng = arrayListOf<WeightedLatLng>()

        for (i in listStates.indices) {
            val ob = listStates[i]
            val nameCity = ob.name
            val casesCity = ob.confirmeds

            city.firstOrNull { it.third == nameCity }?.apply {
                val lat = this.first
                val lng = this.second
                listWeiLng.add(WeightedLatLng(LatLng(lat,lng), casesCity!!.toDouble()))
            }
        }

        return listWeiLng
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}