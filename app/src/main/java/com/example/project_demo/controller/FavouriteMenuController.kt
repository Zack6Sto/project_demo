//package com.example.project_demo.controller
//
//import android.content.Context
//import com.example.project_demo.vo.models.MenuModel
//import org.json.JSONArray
//import org.json.JSONException
//import org.json.JSONObject
//import java.io.IOException
//import java.util.*
//
//
///**
// * Created by Pattarawan_Tha on 12/15/14.
// */
//class FavouriteMenuController(private val context: Context) {
//    private var AllMenuName: Vector<String>? = null
//    private var favMenuModelList: ArrayList<MenuModel?>? = null
//    private var otherMenuModelList: ArrayList<MenuModel?>? = null
//    private var menuConfig: JSONObject? = null
//    private var favMenuConfig: JSONArray? = null
//    var jsonconfig: JSONObject? = null
//    private fun initialConfig() {
//        try {
//            menuConfig = jsonconfig!!.getJSONObject("MenuConfig")
//            favMenuConfig = jsonconfig!!.getJSONArray("FavMenuConfig")
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun loadMenuFromConfig(menu: Vector<String>) {
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_MARKET)) {
//            menu.add("menu1")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_WATCHLIST)) {
//            menu.add("menu2")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_ORDER_ENTRY)) {
//            menu.add("menu3")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_STOCK_GRID)) {
//            menu.add("menu4")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_FIVE_BID_OFFER)) {
//            menu.add("menu5")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_QUOTE)) {
//            menu.add("menu6")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_TICKER)) {
//            menu.add("menu7")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_QUOTE)) {
//            menu.add("menu8")
//        }
//        if (menuConfig!!.optBoolean(GlobalConstant.SCREEN_TICKER)) {
//            menu.add("menu9")
//        }
//    }
//
//    private fun addAllMenu() {
//        AllMenuName = Vector(11)
//        loadMenuFromConfig(AllMenuName!!)
//    }
//
//    private fun addDefaultFavouriteMenu(): Vector<String> {
//        val favmenu = Vector<String>(4)
//        val favSize = favMenuConfig!!.length()
//        for (i in 0 until favSize) {
//            val menuName = favMenuConfig!!.optString(i)
//            when (menuName) {
//                GlobalConstant.SCREEN_MARKET -> favmenu.add(context.resources.getString(R.string.market))
//                GlobalConstant.SCREEN_WATCHLIST -> favmenu.add(context.resources.getString(R.string.watchlist))
//                GlobalConstant.SCREEN_ORDER_ENTRY -> favmenu.add(context.resources.getString(R.string.buysell))
//                GlobalConstant.SCREEN_STOCK_GRID -> favmenu.add(context.resources.getString(R.string.stock_grid))
//                GlobalConstant.SCREEN_FIVE_BID_OFFER -> favmenu.add(context.resources.getString(R.string.fivebidoffer))
//                GlobalConstant.SCREEN_QUOTE -> favmenu.add(context.resources.getString(R.string.quote))
//                GlobalConstant.SCREEN_TICKER -> favmenu.add(context.resources.getString(R.string.ticker))
//                GlobalConstant.SCREEN_VIEW_ORDER -> favmenu.add(context.resources.getString(R.string.vieworder))
//                GlobalConstant.SCREEN_STOCK_CONDITION -> favmenu.add(context.resources.getString(R.string.stock_condition))
//                GlobalConstant.SCREEN_RSS -> favmenu.add(context.resources.getString(R.string.rss))
//                GlobalConstant.SCREEN_PORT_INFO -> favmenu.add(context.resources.getString(R.string.port_info))
//                GlobalConstant.SCREEN_CASH_ONLINE -> favmenu.add(context.resources.getString(R.string.cash_online))
//                GlobalConstant.SCREEN_X_CALENDAR -> favmenu.add(context.resources.getString(R.string.xcalendar))
//                GlobalConstant.SCREEN_STOP_ORDER_ENTRY -> favmenu.add(context.resources.getString(R.string.stop_order))
//                GlobalConstant.SCREEN_VIEW_STOP_ORDER -> favmenu.add(context.resources.getString(R.string.view_stop_order))
//                GlobalConstant.SCREEN_POSITION_LIMIT -> favmenu.add(context.resources.getString(R.string.position_limit))
//                GlobalConstant.SCREEN_CREDIT_BALANCE -> favmenu.add(context.resources.getString(R.string.credit_balance))
//                GlobalConstant.SCREEN_MY_CREDIT_BALANCE -> favmenu.add(context.resources.getString(R.string.credit_balance))
//                GlobalConstant.SCREEN_BORROW_LEND -> favmenu.add(context.resources.getString(R.string.borrow_lend))
//                GlobalConstant.SCREEN_BROADCAST -> favmenu.add(context.resources.getString(R.string.BROADCAST))
//                GlobalConstant.SCREEN_VIEW_OPTION -> favmenu.add(context.resources.getString(R.string.option_greek))
//                GlobalConstant.SCREEN_SYMBOL_CANDLE_CHART -> favmenu.add(
//                    context.resources.getString(
//                        R.string.symbol_candle_chart
//                    )
//                )
//                GlobalConstant.SCREEN_TECHNICAL_CHART -> favmenu.add(context.resources.getString(R.string.technical_chart))
//                GlobalConstant.SCREEN_SUMMARY_REPORT -> favmenu.add(context.resources.getString(R.string.summary_report))
//                GlobalConstant.SCREEN_MIS_REPORT -> favmenu.add(context.resources.getString(R.string.history))
//                GlobalConstant.SCREEN_MY_PORT -> favmenu.add(context.resources.getString(R.string.my_port))
//                GlobalConstant.SCREEN_MY_SBL -> favmenu.add(context.resources.getString(R.string.my_sbl))
//                GlobalConstant.SCREEN_ORDER_SBL_ENTRY -> favmenu.add(context.resources.getString(R.string.order_sbl))
//                GlobalConstant.SCREEN_VIEW_ORDER_SBL -> favmenu.add(context.resources.getString(R.string.view_order_sbl))
//            }
//        }
//        return favmenu
//    }
//
//    private fun loadFavouriteMenu(): Vector<String> {
//        val MS = MenuStrorage(context)
//        try {
//            if (MS.LoadFromStorage()) {
//                if (MS.FavouriteMenu == null || MS.FavouriteMenu.size() === 0) {
//                    addDefaultFavouriteMenu()
//                } else {
//                    return MS.FavouriteMenu
//                }
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return addDefaultFavouriteMenu()
//    }
//
//    private fun addMenu(menuName: String, isFavourite: Boolean): MenuModel? {
//        val DrawableRight =
//            if (isFavourite) context.resources.getResourceEntryName(R.drawable.fav_select) else context.resources.getResourceEntryName(
//                R.drawable.fav_unselect
//            )
//        var LabelMenu: String?
//
//        /* Market */if (menuName == context.resources.getString(R.string.market).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_market),
//                LabelMenu,
//                GlobalConstant.SCREEN_MARKET_MAIN,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.stock_grid).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_grid),
//                LabelMenu,
//                GlobalConstant.SCREEN_STOCK_GRID,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.watchlist).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_watchlist),
//                LabelMenu,
//                GlobalConstant.SCREEN_WATCHLIST,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.buysell).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_buysell),
//                LabelMenu,
//                GlobalConstant.SCREEN_ORDER_ENTRY,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.fivebidoffer).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_fivebidoffer),
//                LabelMenu,
//                GlobalConstant.SCREEN_FIVE_BID_OFFER,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.quote).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_quote),
//                LabelMenu,
//                GlobalConstant.SCREEN_QUOTE,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.ticker).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_ticker),
//                LabelMenu,
//                GlobalConstant.SCREEN_TICKER,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.rss).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_rss),
//                LabelMenu,
//                GlobalConstant.SCREEN_RSS,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.port_info).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_portinfo),
//                LabelMenu,
//                GlobalConstant.SCREEN_ACCOUNT_INFOMATION,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.cash_online).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_cashonline),
//                LabelMenu,
//                GlobalConstant.SCREEN_CASH_ONLINE,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.xcalendar).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_xcalendar),
//                LabelMenu,
//                GlobalConstant.SCREEN_X_CALENDAR,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.stop_order).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_stop_order),
//                LabelMenu,
//                GlobalConstant.SCREEN_STOP_ORDER_ENTRY,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.view_stop_order).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_watchlist),
//                LabelMenu,
//                GlobalConstant.SCREEN_VIEW_STOP_ORDER,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.position_limit).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_position_limit),
//                LabelMenu,
//                GlobalConstant.SCREEN_POSITION_LIMIT,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.credit_balance).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_cashonline),
//                LabelMenu,
//                GlobalConstant.SCREEN_CREDIT_BALANCE,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.my_credit_balance).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_cashonline),
//                LabelMenu,
//                GlobalConstant.SCREEN_MY_CREDIT_BALANCE,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.borrow_lend).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_buysell),
//                LabelMenu,
//                GlobalConstant.SCREEN_BORROW_LEND,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.BROADCAST).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_portinfo),
//                LabelMenu,
//                GlobalConstant.SCREEN_BROADCAST,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.option_greek).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_optionsgreek),
//                LabelMenu,
//                GlobalConstant.SCREEN_VIEW_OPTION,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.symbol_candle_chart).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_market),
//                LabelMenu,
//                GlobalConstant.SCREEN_SYMBOL_CANDLE_CHART,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.technical_chart).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.icon_market),
//                LabelMenu,
//                GlobalConstant.SCREEN_TECHNICAL_CHART,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.summary_report).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.search),
//                LabelMenu,
//                GlobalConstant.SCREEN_SUMMARY_REPORT,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.history).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.search),
//                LabelMenu,
//                GlobalConstant.SCREEN_MIS_REPORT,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.my_port).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.port_info),
//                LabelMenu,
//                GlobalConstant.SCREEN_MY_PORT,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.my_sbl).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.sbl),
//                LabelMenu,
//                GlobalConstant.SCREEN_MY_SBL,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.order_sbl).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.sbl),
//                LabelMenu,
//                GlobalConstant.SCREEN_ORDER_SBL_ENTRY,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName == context.resources.getString(R.string.view_order_sbl).also {
//                LabelMenu = it
//            }) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.sbl),
//                LabelMenu,
//                GlobalConstant.SCREEN_VIEW_ORDER_SBL,
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName.equals(context.resources.getString(R.string.change_password).also {
//                LabelMenu = it
//            }, ignoreCase = true)) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.lock),
//                LabelMenu,
//                "ScreenChangePassword",
//                DrawableRight,
//                isFavourite
//            )
//        } else if (menuName.equals(context.resources.getString(R.string.change_pin).also {
//                LabelMenu = it
//            }, ignoreCase = true)) {
//            return MenuModel(
//                context.resources.getResourceEntryName(R.drawable.lock),
//                LabelMenu,
//                "ScreenChangePin",
//                DrawableRight,
//                isFavourite
//            )
//        }
//        return null
//    }
//
//    private fun createMenu() {
//        val otherMenuName = AllMenuName!!.clone() as Vector<String>
//        val favMenuName = loadFavouriteMenu()
//
//        /* create favourite menuadapter */for (i in favMenuName.indices) {
//            val menuName = favMenuName[i]
//            if (otherMenuName.contains(menuName)) {
//                otherMenuName.remove(menuName)
//            }
//            val menumodel: MenuModel? = addMenu(menuName, true)
//            if (menumodel != null) {
//                if (favMenuModelList == null) {
//                    favMenuModelList = ArrayList<MenuModel?>()
//                }
//                favMenuModelList!!.add(menumodel)
//            }
//        }
//
//        /* create other menuadapter */for (i in otherMenuName.indices) {
//            val menuName = otherMenuName[i]
//            val menumodel: MenuModel? = addMenu(menuName, false)
//            if (menumodel != null) {
//                if (otherMenuModelList == null) {
//                    otherMenuModelList = ArrayList<MenuModel?>()
//                }
//                otherMenuModelList!!.add(menumodel)
//            }
//        }
//    }
//
////    var favouriteMenuAdapter: ArrayList<Any?>?
////        get() = favMenuModelList
////        set(mad) {
////            favMenuModelList = mad
////        }
//
//    @JvmName("setFavouriteMenuAdapter1")
//    fun setFavouriteMenuAdapter(mad: ArrayList<MenuModel?>) {
//        favMenuModelList = mad
//    }
//
//    fun setOtherMenuAdapter(mad: ArrayList<MenuModel?>) {
//        otherMenuModelList = mad
//    }
//
////    var otherMenuAdapter: ArrayList<Any?>?
////        get() = otherMenuModelList
////        set(mad) {
////            otherMenuModelList = mad
////        }
//
//    fun saveFavouriteMenu() {
//        val favMenuName = Vector<String>(
//            favMenuModelList!!.size
//        )
//        for (i in favMenuModelList!!.indices) {
//            favMenuName.add(favMenuModelList!![i]._Label)
//        }
//        val MS = MenuStrorage(context)
//        MS.FavouriteMenu = favMenuName
//        try {
//            MS.WriteToStorage()
//            MS.Close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    init {
//        initialConfig()
//        addAllMenu()
//        createMenu()
//    }
//}