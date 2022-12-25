
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.article.R
import com.article.core.ui.model.ItemTagView
import com.article.databinding.FragmentSelecetTagBottomSheetBinding
import com.article.feature.article.ui.viewmodel.PublishArticleViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable


class PublishTagBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSelecetTagBottomSheetBinding
    private val viewModel: PublishArticleViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val modalBottomSheetBehavior = (dialog as BottomSheetDialog).behavior
        modalBottomSheetBehavior.isDraggable = false
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
    }

    private fun initObserver() {
        viewModel.tags.observe(viewLifecycleOwner) {
            initChip(it)
        }
    }

    private fun initChip(tags: List<ItemTagView>) {
        for (index in tags.indices) {
            val chip = Chip(binding.chipGroupTagsBottomSheetFragment.context)
            chip.text = tags[index].name
            chip.id = tags[index].id
            val drawable = ChipDrawable.createFromAttributes(
                requireContext(),
                null,
                0,
                R.style.MyChip
            )
            chip.setChipDrawable(drawable)
            chip.setOnClickListener {
                viewModel.tagModel.postValue(ItemTagView(id = chip.id, name = chip.text.toString()))
                dismiss()
            }
            binding.chipGroupTagsBottomSheetFragment.addView(chip)
        }
    }

}