package com.example.binlist.ui.binInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.binlist.R
import com.example.binlist.databinding.BininfoFragmentBinding
import com.example.binlist.domain.models.BinInfoItem
import com.example.binlist.ui.BinInfoState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinInfoFragment : Fragment() {

    private var _binding: BininfoFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<BinInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BininfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        /**Блок на случай реализации автаматического поиска,
         * учитвая, что api платное, тестировать его сложнее в таком режиме**/
//        val textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                //viewModel.searchDebounce(s?.toString() ?: " ")
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        }
//
//        binding.searchId.addTextChangedListener(textWatcher)

        binding.apply {
            searchId.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.searchBinInfoByBin(searchId.text.toString())
                    true
                }
                false
            }

            phoneBank.setOnClickListener {
                openPhone(phoneBank.text.toString())
            }

            emailBank.setOnClickListener {
                openEmail(emailBank.text.toString())
            }

        }
    }

    private fun render(state: BinInfoState) {
        when (state) {
            is BinInfoState.Content -> showContent(state.binInfo)
            is BinInfoState.Error -> showEmptyOrError(state.message)
        }
    }

    private fun showEmptyOrError(message: String) = with(binding) {
        placeholderMessage.apply {
            text = message
            isVisible = true
        }
        clearInfo()
    }

    private fun showContent(binInfo: BinInfoItem) = with(binding) {
        clearInfo()
        placeholderMessage.isVisible = false
        country.text = binInfo.countryName
        latitudeLongitudeSt.text =
            requireContext().getString(R.string.coordinates, binInfo.latitude, binInfo.longitude)
        scheme.text = binInfo.scheme
        nameBank.text = binInfo.bankName
        emailBank.text = binInfo.bankUrl
        phoneBank.text = binInfo.bankPhone
        if (binInfo.latitude != null && binInfo.longitude != null) {
            latitudeLongitudeSt.setOnClickListener {
                openMap(binInfo.latitude, binInfo.longitude)
            }
        }

    }

    private fun clearInfo() = with(binding) {
        country.text = ""
        latitudeLongitudeSt.text = ""
        scheme.text = ""
        nameBank.text = ""
        emailBank.text = ""
        phoneBank.text = ""
    }

    private fun openEmail(emailInfo: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailInfo))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        requireContext().startActivity(intent)
    }

    private fun openPhone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }

    private fun openMap(latitude: Int, longitude: Int) {
        val uri = Uri.parse("geo:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}