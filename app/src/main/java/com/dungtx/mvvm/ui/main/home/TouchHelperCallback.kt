package com.dungtx.mvvm.ui.main.home

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TouchHelperCallback: ItemTouchHelper.Callback {

    private var mListener: TouchListener? = null

    constructor(mListener: TouchListener?) {
        this.mListener = mListener
    }

    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        mListener!!.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }
}