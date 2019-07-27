package com.github.veselinazatchepina.listcontent.abstracts

import android.view.View


/**
 * Реализация [AbstractAdapter].
 */
class AdapterImpl<ITEM>(items: List<ITEM>,
                        layoutResId: Int,
                        emptyLayoutResId: Int,
                        private val bindHolder: View.(ITEM) -> Unit
) : AbstractAdapter<ITEM>(items, layoutResId, emptyLayoutResId) {

    private var itemClick: ITEM.(itemView: View, position: Int) -> Unit = { itemView: View, position: Int -> }
    private var longItemClick: ITEM.(itemView: View) -> Unit = {}

    constructor(items: List<ITEM>,
                layoutResId: Int,
                emptyLayoutResId: Int,
                bindHolder: View.(ITEM) -> Unit,
                itemClick: ITEM.(itemView: View, position: Int) -> Unit = { itemView: View, position: Int -> },
                longItemClick: ITEM.(itemView: View) -> Unit = {}) : this(items, layoutResId, emptyLayoutResId, bindHolder) {
        this.itemClick = itemClick
        this.longItemClick = longItemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (items.isNotEmpty()) {
            holder.itemView.bindHolder(items[position])
        }
    }

    override fun onItemClick(itemView: View, position: Int) {
        if (items.isNotEmpty()) {
            items[position].itemClick(itemView, position)
        }
    }

    override fun onLongItemClick(itemView: View, position: Int) {
        if (items.isNotEmpty()) {
            items[position].longItemClick(itemView)
        }
    }
}