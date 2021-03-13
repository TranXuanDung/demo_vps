package com.dungtx.mvvm.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.dungtx.mvvm.R
import com.dungtx.mvvm.data.model.asset.ItemAsset
import com.dungtx.mvvm.data.model.asset.ItemProduct
import com.dungtx.mvvm.data.model.home.Banner
import com.dungtx.mvvm.data.model.home.ItemOutstandingFunction
import com.dungtx.mvvm.data.model.home.ItemRegisterService
import com.dungtx.mvvm.databinding.BottomSheetNetAssetInformationBinding
import com.dungtx.mvvm.databinding.BottomSheetProductBinding
import com.dungtx.mvvm.databinding.FragmentHomeBinding
import com.dungtx.mvvm.ui.base.fragment.BaseFragment
import com.dungtx.mvvm.ui.customview.widget.CircularViewPagerHandler
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment(), BottomAssetAdapter.IAssetAdapter, TouchListener, ProductAdapter.IAssetAdapter {

    private var bottomSheetNetAssetInformation: BottomSheetDialog? = null
    private var bottomSheetProduct: BottomSheetDialog? = null
    private var itemAsset: MutableList<ItemAsset>? = null
    private var itemProduct: MutableList<ItemProduct>? = null
    private var itemProductInHome: MutableList<ItemProduct>? = null
    private var itemOutstandingFunction: MutableList<ItemOutstandingFunction>? = null
    private var itemRegisterService: MutableList<ItemRegisterService>? = null
    private var bottomProductAdapter: BottomProductAdapter? = null
    private var rcProduct: RecyclerView? = null
    private var itemSeeMore: ItemProduct? = null
    private var isEyeOpen = true
    private var continueBannerDisposable: Disposable? = null
    private var dots: MutableList<AppCompatImageView>? = null
    private var dotCount = 0

    override fun getLayoutMain() = R.layout.fragment_home

    override fun setEvents() {
        getBindingData().tvContentNetAssetsAppbar.setOnClickListener {
            showBottomSheetNetAssetInformation(itemAsset!!)
        }

        getBindingData().ivEyeAppbar.setOnClickListener {
            if (isEyeOpen){
                getBindingData().ivEyeAppbar.setImageResource(R.drawable.ic_eye_off)
                getBindingData().tvContentNetAssetsAppbar.text = "1,000,000,000,000"
                isEyeOpen = false
            }else {
                getBindingData().ivEyeAppbar.setImageResource(R.drawable.ic_eye_open)
                getBindingData().tvContentNetAssetsAppbar.text = getString(R.string.content_net_assets)
                isEyeOpen = true
            }
        }
    }

    override fun initComponents() {

        itemAsset = mutableListOf<ItemAsset>()
        itemProduct = mutableListOf<ItemProduct>()
        itemProductInHome = mutableListOf<ItemProduct>()
        itemOutstandingFunction = mutableListOf<ItemOutstandingFunction>()
        itemRegisterService = mutableListOf<ItemRegisterService>()

        initItemAsset()
        initItemProduct()
        initItemOutstandingFunction()
        initItemRegisterService()
        fakeDataBanner()

        getBindingData().rcOutstandingFunction.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        getBindingData().rcOutstandingFunction.adapter = OutstandingFunctionAdapter(itemOutstandingFunction!!)
        (getBindingData().rcOutstandingFunction.adapter as OutstandingFunctionAdapter).notifyDataSetChanged()

        getBindingData().rcRegisterService.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        getBindingData().rcRegisterService.adapter = RegisterServiceAdapter(itemRegisterService!!)
        (getBindingData().rcRegisterService.adapter as RegisterServiceAdapter).notifyDataSetChanged()

        rcProduct = view?.findViewById(R.id.rc_product)
        rcProduct!!.layoutManager = GridLayoutManager(context, 4)
        rcProduct!!.adapter = ProductAdapter(itemProductInHome!!, this)
        (rcProduct!!.adapter as ProductAdapter).notifyDataSetChanged()

        bottomProductAdapter = BottomProductAdapter(itemProduct!!)
    }

    private fun initItemAsset() {
        itemAsset?.add(ItemAsset(1, "Cổ phiếu", 900000000000, 0))
        itemAsset?.add(ItemAsset(2, "Chứng khoán", 49000000000, 1))
        itemAsset?.add(ItemAsset(3, "Trái phiếu", 50000000000, 0))
        itemAsset?.add(ItemAsset(4, "MM", 1000000000, 2))
        itemAsset?.add(ItemAsset(5, "Tài sản tại NHTM", 0, 3))
    }

    private fun initItemProduct() {
        itemProduct!!.add(ItemProduct(1, "Cổ phiếu", R.drawable.ic_shares))
        itemProduct!!.add(ItemProduct(2, "Phái sinh", R.drawable.ic_derivative))
        itemProduct!!.add(ItemProduct(3, "MM", R.drawable.ic_mm))
        itemProduct!!.add(ItemProduct(4, "Trái phiếu", R.drawable.ic_bonds))
        itemProduct!!.add(ItemProduct(5, "Thanh toán", R.drawable.ic_pay))
        itemProduct!!.add(ItemProduct(6, "Bảo hiểm", R.drawable.ic_insurrance))
        itemProduct!!.add(ItemProduct(7, "Giao dịch tiền", R.drawable.ic_money_transactions))

        itemProductInHome!!.addAll(itemProduct!!)
        itemSeeMore = ItemProduct(8, "Xem thêm", R.drawable.ic_see_more)
        itemProductInHome!!.add(itemSeeMore!!)
    }

    private fun initItemOutstandingFunction() {
        itemOutstandingFunction!!.add(ItemOutstandingFunction(1, R.drawable.ic_smartotp))
        itemOutstandingFunction!!.add(ItemOutstandingFunction(2, R.drawable.ic_new))
        itemOutstandingFunction!!.add(ItemOutstandingFunction(3, R.drawable.ic_vps_academy))
        itemOutstandingFunction!!.add(ItemOutstandingFunction(4, R.drawable.ic_smart_robo))
    }


    private fun initItemRegisterService() {
        itemRegisterService!!.add(ItemRegisterService(1, "Phái sinh\nGói thường", R.drawable.ic_derivative_ordinary_packet))
        itemRegisterService!!.add(ItemRegisterService(2, "Margin\nGói nâng cao", R.drawable.ic_margin))
        itemRegisterService!!.add(ItemRegisterService(3, "Mở tài khoản\nTrực tuyến", R.drawable.ic_open_account_online))
        itemRegisterService!!.add(ItemRegisterService(4, "Xác thực\ntải khoản", R.drawable.ic_authentication))
    }

    private fun fakeDataBanner() {
        val banners: MutableList<Banner> = mutableListOf()
        banners.add(Banner(1, "https://nhanlucnganhluat.vn/uploads/images/CE794A57/logo/2019-11/logo.jpg"))
        banners.add(Banner(2, "https://cdn.stockbook.vn/images/posts/LuongCongTuan/medium/bfa96213edaad420f16ef3da338895d86923.jpg"))
        banners.add(Banner(3, "https://i.ytimg.com/vi/KlhV23KVE8A/maxresdefault.jpg"))
        banners.add(Banner(4, "https://muabancophieu.net/wp-content/uploads/2021/01/butphangoanmuc_vi.jpg"))
        banners.add(Banner(5, "https://lh3.googleusercontent.com/proxy/wnGspvE1tNB-xnnfNXM3k40RL6_Zp5QIKClfvkGhfOQoh-I9Av7_bPm5KIDBaasmaTXQrVTZANFVZfLVKxdmh3Jybg680nOtnlCv7wifggsG4QGmtejV"))

        setupBanner(banners)
    }

    private fun setupBanner(banners: MutableList<Banner>) {
        if (banners.isEmpty()) {
            getBindingData().vpBanner.visibility = View.GONE
            getBindingData().llDot.visibility = View.GONE
            return
        }
        setupIndicator(banners)
        val bannerAdapter = BannerAdapter(childFragmentManager, banners)
        getBindingData().vpBanner.adapter = bannerAdapter
        getBindingData().vpBanner.offscreenPageLimit = banners.size
        val circularViewPagerHandler = CircularViewPagerHandler(getBindingData().vpBanner)
        circularViewPagerHandler.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(position: Int) {
                selectIndicator(position)
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
        getBindingData().vpBanner.addOnPageChangeListener(circularViewPagerHandler)
        autoScrollViewPager()
    }

    private fun autoScrollViewPager() {
        if (continueBannerDisposable != null) {
            continueBannerDisposable!!.dispose()
        }
        continueBannerDisposable = Observable.interval(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { getBindingData().vpBanner.currentItem = getBindingData().vpBanner.currentItem + 1 }
    }

    private fun setupIndicator(banners: List<Banner>) {
        val context = context ?: return
        dotCount = banners.size
        dots = ArrayList<AppCompatImageView>()
        getBindingData().llDot.removeAllViews()
        for (i in 0 until dotCount) {
            val dot = AppCompatImageView(context)
            dot.setBackgroundResource(R.drawable.ic_circle_banner_un_selected)
            dots!!.add(dot)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(5, 0, 5, 0)
            getBindingData().llDot.addView(dot, params)
        }
        dots!![0].setBackgroundResource(R.drawable.ic_circle_banner_selected)
    }

    private fun selectIndicator(position: Int) {
        for (i in 0 until dotCount) {
            if (i == position) {
                dots!![position].setBackgroundResource(R.drawable.ic_circle_banner_selected)
            } else {
                dots!![i].setBackgroundResource(R.drawable.ic_circle_banner_un_selected)
            }
        }
    }

    private fun getBindingData() = mBinding as FragmentHomeBinding

    private fun showBottomSheetNetAssetInformation(itemAsset: MutableList<ItemAsset>) {
        hideKeyBoard()
        val binding = BottomSheetNetAssetInformationBinding.inflate(LayoutInflater.from(context))
        bottomSheetNetAssetInformation?.let {
            if (it.isShowing) {
                return
            }
        }

        bottomSheetNetAssetInformation = BottomSheetDialog(context!!, R.style.BottomSheetDialog)
        bottomSheetNetAssetInformation?.setContentView(binding.root)
        val bottomSheet = bottomSheetNetAssetInformation!!.findViewById<View>(R.id.design_bottom_sheet)
        bottomSheet!!.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        var totalMoney: Long = 0
        itemAsset.forEach {
            totalMoney += it.content
        }

        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(totalMoney)
        binding.tvContentNetAsset.text = formattedNumber

        binding.rcDetailContent.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rcDetailContent.adapter = BottomAssetAdapter(this)
        (binding.rcDetailContent.adapter as BottomAssetAdapter).notifyDataSetChanged()

        binding.btnDone.setOnClickListener {
            bottomSheetNetAssetInformation!!.dismiss()
        }

        bottomSheetNetAssetInformation!!.show()
    }

    private fun showBottomSheetProduct(itemProduct: MutableList<ItemProduct>) {
        hideKeyBoard()
        val binding = BottomSheetProductBinding.inflate(LayoutInflater.from(context))
        bottomSheetProduct?.let {
            if (it.isShowing) {
                return
            }
        }

        bottomSheetProduct = BottomSheetDialog(context!!, R.style.BottomSheetDialog)
        bottomSheetProduct?.setContentView(binding.root)
        val bottomSheet = bottomSheetProduct!!.findViewById<View>(R.id.design_bottom_sheet)
        bottomSheet!!.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        binding.rcProduct.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rcProduct.adapter = bottomProductAdapter
        (binding.rcProduct.adapter as BottomProductAdapter).notifyDataSetChanged()
        addItemTouchCallback(binding.rcProduct)

        binding.btnDone.setOnClickListener {
            itemProductInHome!!.clear()
            itemProductInHome!!.addAll(bottomProductAdapter!!.getDataProducts())
            itemProductInHome!!.add(itemSeeMore!!)
            (rcProduct!!.adapter as ProductAdapter).notifyDataSetChanged()
            bottomSheetProduct!!.dismiss()
        }

        bottomSheetProduct!!.show()
    }

    private fun addItemTouchCallback(recyclerView: RecyclerView) {
        val callback: ItemTouchHelper.Callback = TouchHelperCallback(this)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun countNetAsset(): Int {
        if (itemAsset == null || itemAsset?.size == 0){
            return 0
        }else {
            return  itemAsset!!.size
        }
    }

    override fun getDataNetAsset(position: Int): ItemAsset {
        return itemAsset!![position]
    }

    override fun onMove(oldPosition: Int, newPosition: Int) {
        bottomProductAdapter!!.onMove(oldPosition, newPosition)
    }

    override fun onClickItemProduct(position: Int) {
        if (itemProductInHome != null && itemProductInHome!!.size >= 1 && position == itemProductInHome!!.size - 1){
            showBottomSheetProduct(itemProduct!!)
        }
    }

    override fun onDestroy() {
        continueBannerDisposable?.dispose()
        super.onDestroy()
    }
}