package com.article.feature.profile.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.article.core.common.safeNavigate
import com.article.databinding.FragmentRecyclerBookmarkBinding
import com.article.feature.profile.ui.viewmodel.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerBookmarkBinding
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private val viewModel: BookmarkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        bookmarkAdapter = BookmarkAdapter(onItemArticleClickListener = {
            safeNavigate(
                ProfileFragmentDirections.actionProfileFragmentToDetailArticleFragment(it.id)
            )
        })
        binding.rvBookmarkFragment.apply {
            adapter = bookmarkAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun initObserve() {
        viewModel.bookmarks.observe(viewLifecycleOwner) {
            if (it != null) {
                bookmarkAdapter.submitList(it)
            }
        }
    }
}