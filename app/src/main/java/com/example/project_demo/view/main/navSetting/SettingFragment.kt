package com.example.project_demo.view.main.navSetting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.project_demo.R
import com.example.project_demo.adapter.MenuAdapter
import com.example.project_demo.adapter.OnClickBackView
import com.example.project_demo.databinding.FragmentSettingBinding
import com.example.project_demo.utils.library.dslv.DragSortListView
import com.example.project_demo.view.base.BaseFragment
import com.example.project_demo.view.login.LoginActivity
import com.example.project_demo.view.main.MainActivity
import com.example.project_demo.vo.ModelSetting
import com.example.project_demo.vo.models.MenuModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class SettingFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingBinding

    private val viewModel: NavSettingViewModel by viewModel()

    private val mListMenuSetting = ArrayList<ModelSetting>()

    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val TAG: String = "SettingFragment"

    //    private val menuListenerTable: ArrayList<OnMenuSelect>? = null
//    private val comparator: Comparator<MenuModel>? = null
    private val useDragMenu = false
//    private val favMenuController: FavouriteMenuController? = null

    private val mAdapterSetting by lazy {
        AdapterSetting(requireContext(), mListMenuSetting, viewModel.mOnClickItemSetting)
    }

    companion object {
        fun newInstance(loadPage: String? = ""): SettingFragment {
            val args = Bundle()
            args.putString("keyParam", loadPage)
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //สีstatusBar
//        requireActivity().window.statusBarColor = Color.parseColor("#FFBB86FC")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.dataViewModel = viewModel

        initView()
        onClickListener()
    }

    private fun initView() {
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        binding.tvEmail.text = user!!.email
        binding.tvUid.text = user.uid

        mAuthListener = FirebaseAuth.AuthStateListener {
            val users = it.currentUser
            if (users== null){
                startActivity(Intent(requireContext(),LoginActivity::class.java))
            }
        }
    }

    private fun onClickListener() {
        viewModel.mOnClickListener.observe(requireActivity(), androidx.lifecycle.Observer {
            when (it) {
                "logout" -> {
                    mAuth!!.signOut()
                    Toast.makeText(requireContext(), "Singed out!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"Singed out!")
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }

//    private fun RequestNewPin() {
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle(R.string.confirm_req_pin)
//        //        builder.setMessage();
//        builder.setCancelable(false)
//        builder.setPositiveButton(R.string.send,
//            DialogInterface.OnClickListener { dialog, id -> SendRequestNewPin() })
//        builder.setNegativeButton(R.string.close,
//            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//        val alertDialog = builder.create()
//        alertDialog.show()
//    }

    private fun initMenu() {
        val listMenu: DragSortListView = binding.MenuListView
        val listFavMenu: DragSortListView = binding.FavMenuListView

        val favButtonOnClick: OnClickBackView = object : OnClickBackView {
            override fun OnClickButton(Position: Int, v: View?) {
                if (useDragMenu) return
                val model: MenuModel
                val favMenuAdapter: MenuAdapter = listFavMenu.inputAdapter as MenuAdapter
                val menuAdapter: MenuAdapter = listMenu.inputAdapter as MenuAdapter
                if (v != null) {
                    if (v.isSelected) {
                        if (favMenuAdapter.getMenuModelList()!!.size > 1) {
                            model = favMenuAdapter.getItem(Position)!!
                            model._isFavouriteRow = false
                            model._DrawableRight =
                                resources.getResourceEntryName(R.drawable.ic_favorite_border)
                            favMenuAdapter.remove(model)
                            menuAdapter.add(model)
//                            if (favMenuController != null) {
//                                favMenuController.saveFavouriteMenu()
//                            }
                        } else {
                            Toast.makeText(
                                activity!!.applicationContext,
                                "Favourite menu at least is 1",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        val MAX = 5
                        if (favMenuAdapter.getMenuModelList()!!.size < MAX) {
                            model = menuAdapter.getItem(Position)!!
                            model._isFavouriteRow = true
                            model._DrawableRight = resources.getResourceEntryName(R.drawable.ic_favorite)
                            menuAdapter.remove(model)
                            favMenuAdapter.add(model)
                        } else {
                            Toast.makeText(
                                activity!!.applicationContext,
                                "Favourite Menu Maximum is $MAX", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                setListViewHeight(listFavMenu)
                setListViewHeight(listMenu)
                if (listFavMenu.inputAdapter.count <= 5) {
//                    favMenuController!!.setFavouriteMenuAdapter(favMenuAdapter.getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                    favMenuController.setOtherMenuAdapter(menuAdapter.getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                    (activity as MainActivity?).favouriteMenuChanged(favMenuAdapter.getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                    favMenuController.saveFavouriteMenu()
                }
            }
        }

//        val FavMenuList: java.util.ArrayList<MenuModel> =
//            favMenuController.getFavouriteMenuAdapter()
//        if (FavMenuList != null) {
//            val FMAD = MenuAdapter(activity, R.layout.spinner_layout, FavMenuList)
//            //            MenuAdapter FMAD = new MenuAdapter(getActivity(), android.R.layout.simple_list_item_1, FavMenuList);
//            ListFavMenu.setAdapter(FMAD)
//            ListFavMenu.refreshDrawableState()
//            ListFavMenu.setDragEnabled(false)
//            setListViewHeight(ListFavMenu)
//            ListFavMenu.setDropListener(object : DropListener() {
//                fun drop(from: Int, to: Int) {
//                    val favmenuAdapter: MenuAdapter = ListFavMenu.getInputAdapter() as MenuAdapter
//                    val model: MenuModel = favmenuAdapter.getItem(from)
//                    favmenuAdapter.remove(model)
//                    favmenuAdapter.insert(model, to)
//                    favmenuAdapter.notifyDataSetChanged()
//                    (activity as MobileTrade?).favouriteMenuChanged((ListFavMenu.getInputAdapter() as MenuAdapter).getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                }
//            })
//            ListFavMenu.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//                val Obj = adapterView.getItemAtPosition(i)
//                if (Obj is MenuModel) {
//                    val Model: MenuModel = Obj as MenuModel
//                    val scr: Screen = ClassUtil.getScreenClass(Model._Class)
//                    if (scr != null) {
//                        InvokeListener(scr, Model._Label)
//                    }
//                }
//            })
//            FMAD.setOnClickBackView(FavButtonOnClick)
//        }
//        val MenuList: java.util.ArrayList<MenuModel> = favMenuController.getOtherMenuAdapter()
//        if (MenuList != null) {
//            sortMenuModel(MenuList)
//            //            MenuAdapter MAD = new MenuAdapter(getActivity(), android.R.layout.simple_list_item_1, MenuList);
//            val MAD = MenuAdapter(activity, R.layout.spinner_layout, MenuList)
//            ListMenu.setAdapter(MAD)
//            ListMenu.setDragEnabled(false)
//            setListViewHeight(ListMenu)
//            ListMenu.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//                val Obj = adapterView.getItemAtPosition(i)
//                if (Obj is MenuModel) {
//                    val Model: MenuModel = Obj as MenuModel
//                    val scr: Screen = ClassUtil.getScreenClass(Model._Class)
//                    if (scr != null) {
//                        InvokeListener(scr, Model._Label)
//                    }
//                }
//            })
//            MAD.setOnClickBackView(FavButtonOnClick)
//        }
//        val EditFavMenu: TextView = getDragFavMenuButton()
//        EditFavMenu.setOnClickListener { view ->
//            if (!view.isSelected) {
//                useDrag(true)
//            } else {
//                useDrag(false)
//                (activity as MobileTrade?).favouriteMenuChanged((ListFavMenu.getInputAdapter() as MenuAdapter).getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                if (favMenuController != null) {
//                    favMenuController.saveFavouriteMenu()
//                    favMenuController.setFavouriteMenuAdapter((ListFavMenu.getInputAdapter() as MenuAdapter).getMenuModelList() as java.util.ArrayList<MenuModel?>)
//                }
//            }
//            view.isSelected = if (!view.isSelected) true else false
//        }
//    }
    }

    fun setListViewHeight(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = 0
        for (size in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(size, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
    }
}