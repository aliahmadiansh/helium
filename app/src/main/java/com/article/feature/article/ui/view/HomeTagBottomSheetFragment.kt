package com.article.feature.article.ui.view

import com.article.feature.article.ui.viewmodel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.article.R
import com.article.core.ui.model.ItemTagView
import com.article.databinding.FragmentSelecetTagBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTagBottomSheetFragment :
    BottomSheetDialogFragment() {

    private val viewModel: HomeViewModel by viewModels({ requireParentFragment() })
    private lateinit var binding: FragmentSelecetTagBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val modalBottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        modalBottomSheetBehavior.isDraggable = true
        binding = FragmentSelecetTagBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {

        binding.ivCloseBottomSheetFragment.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnSelectTagBottomSheet.setOnClickListener {
            viewModel.tagsViewFilter.value = viewModel.tagsList.value?.filter {
                binding.chipGroupTagsBottomSheetFragment.checkedChipIds.contains(it.id)
            }?.toMutableList()
            dismiss()
        }
    }

    private fun initObserver() {
        viewModel.tagsList.observe(viewLifecycleOwner) {
            initChip(it)
        }
    }

    private fun initChip(tags: List<ItemTagView>) {
        tags.forEach { itemTagView ->
            val chip = Chip(requireContext())
            val drawable = ChipDrawable
                .createFromAttributes(
                    requireContext(),
                    null,
                    0,
                    R.style.MyChip
                )
            chip.apply {
                id = itemTagView.id
                text = itemTagView.name
                setChipDrawable(drawable)
                viewModel.tagsViewFilter.value?.let {
                    isChecked = it.contains(itemTagView)
                }
            }
            binding.chipGroupTagsBottomSheetFragment.addView(chip)
        }
    }

    companion object {
        const val TAG = "selectTagBottomSheetFragment"
    }
}