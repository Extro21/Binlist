package com.example.binlist.ui.historyBin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binlist.databinding.HistoryFragmentBinding
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.ui.HistoryState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {
    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter = HistoryAdapter()
    private val viewModel by viewModel<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            rvHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHistory.adapter = adapter
        }

        viewModel.getHistory()

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }


    }

    private fun render(state: HistoryState) {
        when (state) {
            is HistoryState.Content -> showContent(state.items)
            is HistoryState.Empty -> showEmpty()
        }
    }

    private fun showEmpty() {
        binding.placeholderMessageEmpty.isVisible = true
    }

    private fun showContent(items: List<BinInfoItem>) {
        binding.placeholderMessageEmpty.isVisible = false
        adapter.submitList(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}